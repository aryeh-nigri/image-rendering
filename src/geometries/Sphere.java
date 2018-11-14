/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Defines a Sphere.
 * @author Arieh Nigri
 */
public class Sphere extends RadialGeometry {
    
    private Point3D center;

    public Sphere() {
        this.center = new Point3D();
    }
    
    public Sphere(Point3D center, double rad) {
        super(rad);
        this.center = new Point3D(center);
    }
    
    public Sphere(double rad, Point3D center){
        this(center, rad);
    }
    
    public Sphere(Sphere obj) {
        super(obj);
        this.center = obj.getCenter();
    }
    
    public Sphere(Map<String, String> attributes){}

    public Point3D getCenter() {
        return new Point3D(center);
    }

    public void setCenter(Point3D center) {
        this.center = new Point3D(center);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {        
        //Ray: P = P0 + tV
        //Sphere: |P - O|2 - r2 = 0
        

        //L = O - P0
        Vector L = new Vector(ray.getPOO(), this.center);
        
        //tm = L ・V
        double tm = L.dotProduct(ray.getDirection());
        
        //d = (|L|2 - tm2)0.5
        double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2));

        //if d > r -> no intersections
        if(d > this.radius)
        {
            return new ArrayList<>(0);   //   as an empty list
        }
        
        List<Point3D> intersectionPoints = new ArrayList<>();
                
        //th = (r2 - d2)0.5
        double th = Math.sqrt(Math.pow(this.radius, 2) - Math.pow(d, 2));
        
        //t1 = tm - th
        double t1 = tm - th;
        
        //t2 = tm + th
        double t2 = tm + th;
        
        //P1 = P0 + t1V        
        //P2 = P0 + t2V
        //take only t > 0
        if(t1 > 0)
        {
            Point3D P1 = ray.getPOO().add(ray.getDirection().scaling(t1));
            intersectionPoints.add(P1);
        }
        
        if(t2 > 0)
        {
            Point3D P2 = ray.getPOO().add(ray.getDirection().scaling(t2));
            intersectionPoints.add(P2);
        }
        
        return intersectionPoints;
    }

    @Override
    public Vector getNormal(Point3D point) {
        //V = P- O 
        //N = V / ||V||
        
        Vector N = new Vector(this.center, point);
        N.normalize();
        
        return N;
    }
    
}
