/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Defines a Plane.
 * @author Arieh Nigri
 */
public class Plane extends Geometry implements FlatGeometry {
    
    private Vector N;
    private Point3D Q;

    public Plane() {
        this.N = new Vector();
        this.Q = new Point3D();
    }
    
    public Plane(Vector N, Point3D Q) {
        this.N = new Vector(N);
        this.N.normalize();
        this.Q = new Point3D(Q);
    }
    
    public Plane(Point3D Q, Vector N) {
        this(N, Q);
    }
    
    public Plane(Plane obj) {
        super(obj);
        this.N = obj.getN();
        this.Q = obj.getQ();
    }

    public Vector getN() {
        return new Vector(N);
    }

    public void setN(Vector N) {
        this.N = new Vector(N);
        this.N.normalize();
    }

    public Point3D getQ() {
        return new Point3D(Q);
    }

    public void setQ(Point3D Q) {
        this.Q = new Point3D(Q);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        //Ray: P = P0 + tV
        //Plane: N・(P - Q0) = 0
        //N・(P0 + tV - Q0) = 0
        //t = - N・(P0 - Q0)/(N・V)
        
        List<Point3D> intersectionPoints = new ArrayList<>();
        
        Vector vectorAtPlane = new Vector(this.Q, ray.getPOO());
        
        double vectorsProduct = this.N.dotProduct(ray.getDirection());
        
        if(vectorsProduct != 0.0)   //   not perpendicular
        {
            double t = -1.0 * (this.N.dotProduct(vectorAtPlane) / vectorsProduct);
            if(t > 0.0)
            {
                Point3D P = ray.getPOO().add(ray.getDirection().scaling(t));
                intersectionPoints.add(P);
            }
        }
        
        return intersectionPoints;
    }

    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = new Vector(this.N);
        return normal;
    }
    
}
