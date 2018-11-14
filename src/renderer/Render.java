/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import elements.LightSource;
import geometries.FlatGeometry;
import geometries.Geometry;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * Defines a Render, that makes the rendering of images.
 * @author arieh_nigri
 */
public class Render {
    
    private Scene scene;
    private ImageWriter imageWriter;
    private final int RECURSION_LEVEL = 2;   //   max level of recursion
    private final int MAX_ANTI_ALIASING = 8;   //   max number of rays to generate

    public Render(){
        this.scene = new Scene();
        this.imageWriter = new ImageWriter("default_render", 500, 500, 50, 50);
    }
    
    public Render(Scene scene, ImageWriter imageWriter) {
        this.scene = new Scene(scene);
        this.imageWriter = new ImageWriter(imageWriter);
    }
    
    public Render(ImageWriter imageWriter, Scene scene){
        this(scene, imageWriter);
    }
    
    public Render(Render obj){
        this.scene = obj.getScene();
        this.imageWriter = obj.getImageWriter();
    }

    public Scene getScene() {
        return new Scene(scene);
    }

    public void setScene(Scene scene) {
        this.scene = new Scene(scene);
    }

    public ImageWriter getImageWriter() {
        return new ImageWriter(imageWriter);
    }

    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = new ImageWriter(imageWriter);
    }
    
    public void writeToImage(){
        this.imageWriter.writeToimage();
    }
    
    /**
     * Rendering an image by iterating through every pixel in our view plane, defined
     * by width and height from the imageWriter object, constructing a Ray throw it
     * and looking for intersections with any Geometry from the scene object. For pixels
     * with no intersections, the background color of the scene is painted, and for pixels
     * with intersections are painted by calculating the color at the closest point to the
     * viewer(the camera object from the scene).
     */
    public void renderImage(){
        
        for(int i = 0; i < this.imageWriter.getWidth(); i++)
        {
            for(int j = 0; j < this.imageWriter.getHeight(); j++)
            {
                Ray ray = this.scene.getCamera().constructRayThroughPixel
                                                (this.imageWriter.getNx(),
                                                 this.imageWriter.getNy(),
                                                 i,
                                                 j,
                                                 this.scene.getScreenDistance(),
                                                 this.imageWriter.getWidth(),
                                                 this.imageWriter.getHeight()
                                                );
                Map<Geometry, List<Point3D>> intersectionPoints = this.getSceneRayIntersections(ray);
                if(intersectionPoints.isEmpty())
                {
                    this.imageWriter.writePixel(i, j, this.scene.getBackground());
                }
                else
                {
                    Map<Geometry, Point3D> closestPoint = this.getClosestPoint(intersectionPoints);
                    Geometry g = (Geometry) closestPoint.keySet().toArray()[0];
                    Point3D p = closestPoint.get(g);
                    this.imageWriter.writePixel(i, j, this.calcColor(g, p, ray));
                }
            }
        }
    }
    
    /**
     * This method render an image as the renderImage does, with the difference in the number
     * of rays that are created through every pixel. Additional rays are created and given a certain weight,
     * and the final color is calculated according to the colors and weight of the color from every ray.
     * Rendering images anti aliased can be more real like, with edges less jagged, but also takes more time 
     * to render than the renderImage method.
     * @param antialiasing Number of additional rays to construct over a pixel.
     * @see renderImage
     */
    public void renderImageAntiAliasing(int antialiasing){
        if(antialiasing <= 0)   //   negative not allowed
        {
            this.renderImage();   //   simply calls the rendering without aliasing, and ends
            return;
        }
        else if(antialiasing > MAX_ANTI_ALIASING)   //   to prevent days of rendering
            antialiasing = MAX_ANTI_ALIASING;
        
        for(int i = 0; i < this.imageWriter.getWidth(); i++)
        {
            for(int j = 0; j < this.imageWriter.getHeight(); j++)
            {
                Ray originalRay = this.scene.getCamera().constructRayThroughPixel
                                                        (this.imageWriter.getNx(),
                                                         this.imageWriter.getNy(),
                                                         i,
                                                         j,
                                                         this.scene.getScreenDistance(),
                                                         this.imageWriter.getWidth(),
                                                         this.imageWriter.getHeight()
                                                        );
                //   this ray always weights twice the weight of the rest
                double originalWeight = 2.0 / (1.0 + antialiasing);
                
                Map<Ray, Double> rays = new HashMap<>(antialiasing + 1);
                
                rays.put(originalRay, originalWeight);
                
                for(int k = 0; k < antialiasing; k++)
                {
                    Random rand = new Random();
                    double xVariation = rand.nextDouble() - 0.5;   //   -0.5 to 0.5
                    double yVariation = rand.nextDouble() - 0.5;   //   -0.5 to 0.5
                    Ray ray = this.scene.getCamera().constructRayThroughPixel
                                                (this.imageWriter.getNx(),
                                                 this.imageWriter.getNy(),
                                                 i + xVariation,
                                                 j + yVariation,
                                                 this.scene.getScreenDistance(),
                                                 this.imageWriter.getWidth(),
                                                 this.imageWriter.getHeight()
                                                );
                    double distributedWeight = (1.0 - originalWeight) / antialiasing;
                    rays.put(ray, distributedWeight);
                }// k for
                
                Color pixelColor = Color.BLACK;
                
                for(Entry<Ray, Double> weightedRay : rays.entrySet())
                {
                    Map<Geometry, List<Point3D>> intersectionPoints = this.getSceneRayIntersections(weightedRay.getKey());
                    if(intersectionPoints.isEmpty())
                    {
                        this.imageWriter.writePixel(i, j, this.scene.getBackground());
                        Color c = Render.scaleColors(this.scene.getBackground(), weightedRay.getValue());
                        pixelColor = Render.addColors(pixelColor, c);
                    }
                    else
                    {
                        Map<Geometry, Point3D> closestPoint = this.getClosestPoint(intersectionPoints);
                        Geometry g = (Geometry) closestPoint.keySet().toArray()[0];
                        Point3D p = closestPoint.get(g);
                        Color c = this.calcColor(g, p, weightedRay.getKey());
                        c = Render.scaleColors(c, weightedRay.getValue());
                        pixelColor = Render.addColors(pixelColor, c);
                    }
                }//rays
                
                this.imageWriter.writePixel(i, j, pixelColor);
                
            }// j for
        }// i for
    }
    
    /**
     * Generates a grid image, painting WHITE every interval pixels.
     * @param interval Distance between lines of the grid.
     */
    public void printGrid(int interval){
        
        for(int i = 1; i < this.imageWriter.getWidth(); i++)
        {
            for(int j = 1; j < this.imageWriter.getHeight(); j++)
            {
                if(i % interval == 0 || j % interval == 0)
                {
                    this.imageWriter.writePixel(i, j, Color.WHITE);
                }
            }//outer for
        }//inner for
    }
    
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay){
        return this.calcColor(geometry, point, inRay, 0);
    }
    
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){
        //   recursion condition to stop
        if(level >= RECURSION_LEVEL)
            return Color.BLACK;
        
        //Ipoint3D = IE + Kam*Iam +∑i [ Kd*(N • L)*ILi + Ks*(V • R)ˆn*ILi ]
        Color ambientLight = this.scene.getAmbientLight().getIntensity();
        Color emissionLight = geometry.getEmission();
        
        //Color IO = Render.addColors(ambientLight, emissionLight);   //   IE + Kam*Iam
        
        Iterator<LightSource> lights = this.scene.getLightsIterator();
        
        //   constants from   ∑i Kd*(N • Li)*ILi + Ks*(V • R)ˆn*ILi
        double Kd = geometry.getMaterial().getKd();
        double Ks = geometry.getMaterial().getKs();
        Vector N = geometry.getNormal(point);
        Vector V = new Vector(point, this.scene.getCamera().getP0());
        V.normalize();
        int n = geometry.getnShininess();
        
        Color diffuseLight = Color.BLACK;
        Color specularLight = Color.BLACK;
        
        while(lights.hasNext())
        {
            LightSource light = lights.next();
            
            if (!occluded(light, point, geometry)){
                //   Kd*(N • L)*IL
                Color diff = this.calcDiffuseComp(Kd, N, light.getL(point), light.getIntensity(point));
                //   Ks*(V • R)ˆn*IL
                Color spec = this.calcSpecularComp(Ks, V, N, light.getL(point), n, light.getIntensity(point));

                diffuseLight = Render.addColors(diffuseLight, diff);
                specularLight = Render.addColors(specularLight, spec);
            }
        }
        
        // Recursive call for a reflected ray
        Color reflectedLight = Color.BLACK;
        Ray reflectedRay = constructReflectedRay(N, point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay);
        if(reflectedEntry != null)   //   reflected ray dont hit any geometry
        {
            Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double Kr = geometry.getMaterial().getKr();
            reflectedLight = Render.scaleColors(reflectedColor, Kr);
        }

        // Recursive call for a refracted ray
        Color refractedLight = Color.BLACK;
        Ray refractedRay = constructRefractedRay(N, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);
        if(refractedEntry != null)
        {
            Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double Kt = geometry.getMaterial().getKt();
            refractedLight = Render.scaleColors(refractedColor, Kt);
        }
        
        Color IO = Render.addColors(new Color[] {ambientLight, emissionLight, diffuseLight, specularLight, reflectedLight, refractedLight});
        return IO;
    }
    
    private Color calcDiffuseComp(double Kd, Vector N, Vector L, Color IL){
        //Kd*(N • L)*IL
        if(Kd == 0.0)
            return Color.BLACK;
        
        double angle = N.dotProduct(L) * -1.0;   //   N and L should be in opposite directions, 
                                                 //   so we need to invert the dot product
        if(angle <= 0.0)   //   angle >= 90
            return Color.BLACK;
        
        double scalar = angle * Kd;
          
        Color diffuseColor = Render.scaleColors(IL, scalar);
        return diffuseColor;
    }
    
    private Color calcSpecularComp(double Ks, Vector V, Vector N, Vector D, int shininess, Color IL){
        //Ks*(V • R)ˆn*IL
        if(Ks == 0.0)
            return Color.BLACK;
        
        //R = D - 2(D • N)N
        double alpha = D.dotProduct(N);
        if(alpha >= 0.0)
            return Color.BLACK;
        
        Vector R = D.subtract(N.scaling(2.0 * alpha));
        R.normalize();
        
        double angle = V.dotProduct(R);
        if(angle <= 0.0)   //   angle >= 90˚
            return Color.BLACK;
        
        double exp = Math.pow(angle, shininess);
        
        double scalar = exp * Ks;
        
        Color specColor = Render.scaleColors(IL, scalar);
        return specColor;
    }
    
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints){
        double distance = Double.MAX_VALUE;
        Point3D P0 = this.scene.getCamera().getP0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<>();
        
        for(Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
        {
            for(Point3D point : entry.getValue())
            {
                if(P0.distance(point) < distance)
                {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }//if
            }//inner for
        }//outer for
        
        return minDistancePoint;
    }
    
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){
        
        Iterator<Geometry> geometries = this.scene.getGeometriesIterator();
        
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<>();
        
        while(geometries.hasNext())
        {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            if(!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        
        return intersectionPoints;
    }
    
    /**
     *
     * @param c1 First Color to add
     * @param c2 Second Color to add
     * @return The resulted <b>Color</b> between the sum of the given Colors.
     * This method makes sure that won't be over or underflow, regardless the input.
     */
    public static Color addColors(Color c1, Color c2){
        int r = c1.getRed() + c2.getRed();
        int g = c1.getGreen() + c2.getGreen();
        int b = c1.getBlue() + c2.getBlue();
        
        if(r > 255)
            r = 255;
        if(g > 255)
            g = 255;
        if(b > 255)
            b = 255;
        
        return new Color(r, g, b);
    }
    
    /**
     *
     * @param colors Array of Colors to sum.
     * @return The resulted Color from the sum of all Colors in the array.
     */
    public static Color addColors(Color[] colors){
        int r = 0;
        int g = 0;
        int b = 0;
        
        for(Color c : colors){
            r += c.getRed();
            g += c.getGreen();
            b += c.getBlue();
        }
        
        if(r > 255)
            r = 255;
        if(g > 255)
            g = 255;
        if(b > 255)
            b = 255;
        
        return new Color(r, g, b);
    }
    
    /**
     *
     * @param color Color to be scaled by a value
     * @param value Value to scale a Color
     * @return The resulted scaled <b>Color</b> from the multiplication of the parameters.
     * This method makes sure that won't be over or underflow, regardless the input.
     */
    public static Color scaleColors(Color color, double value){
        if(value <= 0.0)
            return Color.BLACK;
        
        int r = (int) Math.round(color.getRed() * value);
        int g = (int) Math.round(color.getGreen() * value);
        int b = (int) Math.round(color.getBlue() * value);
        
        if(r > 255)
            r = 255;
        if(g > 255)
            g = 255;
        if(b > 255)
            b = 255;
        
        return new Color(r, g, b);
    }

    @Override
    public String toString() {
        return "Render{" + "scene=" + scene + ", imageWriter=" + imageWriter + '}';
    }

    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {
        Vector lightDirection = light.getL(point).scaling(-1.0);
        
        Vector epsilonVector = geometry.getNormal(point);   //   .scaling(2.0);
        
        Point3D geometryPoint = point.add(epsilonVector);
        
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        
        Map<Geometry, List<Point3D>> intersectionPoints = this.getSceneRayIntersections(lightRay);
        
        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry)
        {
            intersectionPoints.remove(geometry);
        }
        
        for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet())
        {
            if(entry.getKey().getMaterial().getKt() == 0.0)
                return true;
        }
        return false;
        //return !intersectionPoints.isEmpty();
    }
    
    private Ray constructReflectedRay(Vector N, Point3D p, Ray inRay) {
        // R = D - 2(D • N)N
        Vector D = inRay.getDirection();
        double angle = D.dotProduct(N);
        Vector direction = D.subtract(N.scaling(2.0 * angle));
        
        Vector eps;
        if(direction.dotProduct(N) < 0)
            eps = N.scaling(-2.0);
        else
            eps = N.scaling(2.0);
        
        //Vector eps = new Vector();
        Point3D poo = p.add(eps);
        Ray R = new Ray(poo, direction);
        return R;
    }

    private Ray constructRefractedRay(Vector N, Point3D p, Ray inRay) {
        // R=(n1/n2)(cosϴi-cosϴr)N-(n1/n2)D
        Vector direction = inRay.getDirection();
        
        Vector eps;
        if(direction.dotProduct(N) < 0)
            return null;//eps = N.scaling(-2.0);
        else
            eps = N.scaling(2.0);
        
        Point3D poo = p.add(eps);
        Ray R = new Ray(poo, direction);
        return R;
    }

    private Entry<Geometry, Point3D> findClosestIntersection(Ray ray) {
        if(ray == null)
            return null;
        Iterator<Geometry> geometries = this.scene.getGeometriesIterator();
        
        Entry<Geometry, Point3D> intersectionPoint = null;
        
        while(geometries.hasNext())
        {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            if(!geometryIntersectionPoints.isEmpty()){
                intersectionPoint = new AbstractMap.SimpleEntry<>(geometry, geometryIntersectionPoints.get(0));
                break;
            }
        }
        
        return intersectionPoint;
    }
    
}
