/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.util.ArrayList;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Defines a cylinder.
 * @author Arieh Nigri
 */
public class Cylinder extends RadialGeometry {
    
    private Point3D axisPoint;
    private Vector axisDirection;

    public Cylinder() {
        this.axisPoint = new Point3D();
        this.axisDirection = new Vector();
    }
    
    public Cylinder(Point3D axisPoint, Vector axisDirection, double rad) {
        super(rad);
        this.axisPoint = new Point3D(axisPoint);
        this.axisDirection = new Vector(axisDirection);
    }
    
    public Cylinder(Cylinder obj) {
        super(obj);
        this.axisPoint = obj.getAxisPoint();
        this.axisDirection = obj.getAxisDirection();
    }

    public Point3D getAxisPoint() {
        return new Point3D(axisPoint);
    }

    public void setAxisPoint(Point3D axisPoint) {
        this.axisPoint = new Point3D(axisPoint);
    }

    public Vector getAxisDirection() {
        return new Vector(axisDirection);
    }

    public void setAxisDirection(Vector axisDirection) {
        this.axisDirection = new Vector(axisDirection);
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray ray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector getNormal(Point3D point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
