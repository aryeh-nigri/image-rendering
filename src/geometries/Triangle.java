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
 * Defines a Triangle.
 * @author arieh_nigri
 */
public class Triangle extends Geometry implements FlatGeometry{
    
    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    public Triangle() {
        this.p1 = new Point3D();
        this.p2 = new Point3D();
        this.p3 = new Point3D();
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = new Point3D(p1);
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
    }
    
    public Triangle(Triangle obj){
        super(obj);
        this.p1 = obj.getP1();
        this.p2 = obj.getP2();
        this.p3 = obj.getP3();
    }
    
    public Triangle(Map<String, String> attributes){}

    public Point3D getP1() {
        return new Point3D(p1);
    }

    public void setP1(Point3D p1) {
        this.p1 = new Point3D(p1);
    }

    public Point3D getP2() {
        return new Point3D(p2);
    }

    public void setP2(Point3D p2) {
        this.p2 = new Point3D(p2);
    }

    public Point3D getP3() {
        return new Point3D(p3);
    }

    public void setP3(Point3D p3) {
        this.p3 = new Point3D(p3);
    }

    @Override
    public String toString() {
        return "Triangle{" + "p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        //For each side of the triangle:
        //V1=T1-P0
        //V2=T2-P0
        //N1=(V2xV1)/|V2xV1|
        //Then,
        //if sign((P-P0)・N1) ==
        //sign((P-P0)・N2) ==
        //sign((P-P0)・N3)
        //-> return true
        
        Vector normal = this.getNormal(this.p1);
        
        Plane planeTriangle = new Plane(normal, this.p1);
        
        List<Point3D> planeTrianglePoint = planeTriangle.findIntersections(ray);
        
        if(planeTrianglePoint.isEmpty() == true)
        {
            return new ArrayList<>(0);
        }
        
        Point3D P = planeTrianglePoint.get(0);
                
        Vector V1 = new Vector(ray.getPOO(), this.p1);
        Vector V2 = new Vector(ray.getPOO(), this.p2);
        Vector V3 = new Vector(ray.getPOO(), this.p3);
        
        Vector N1 = V2.crossProduct(V1);
        N1.normalize();
        Vector N2 = V1.crossProduct(V3);
        N2.normalize();
        Vector N3 = V3.crossProduct(V2);
        N3.normalize();
        
        //   P - P0
        Vector PoToP = new Vector(ray.getPOO(), P);
        
        if(Math.signum(PoToP.dotProduct(N1)) == Math.signum(PoToP.dotProduct(N2)) &&
           Math.signum(PoToP.dotProduct(N2)) == Math.signum(PoToP.dotProduct(N3)))
        {
            return planeTrianglePoint;
        }
        else
        {
            return new ArrayList<>(0);   //   as an empty list
        }
    }

    @Override
    public Vector getNormal(Point3D point) {
        //V1 = P2 - P1
        //V2 = P3 - P1
        //N = ( V1 x V2 ) / || V1 x V2 ||
        
        Vector V1 = new Vector(this.p1, this.p2);
        Vector V2 = new Vector(this.p1, this.p3);
        
        Vector N = V1.crossProduct(V2);
        N.normalize();
        
        return N;
    }
    
}
