/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Defines a Camera, as the viewer of a Scene.
 * @author Arieh Nigri
 */
public class Camera {
    
    private Point3D P0;   //   Eye point of the camera
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;   //   Should be calculated as the cross product of vUp and vTo
    
    public Camera() {
        this.P0 = new Point3D();
        this.vUp = new Vector(0.0, 1.0, 0.0);
        this.vTo = new Vector(0.0, 0.0, -1.0);
        this.vRight = new Vector(1.0, 0.0, 0.0);
    }
    
    public Camera(Point3D P0, Vector vUp, Vector vTo) {
        this.P0 = new Point3D(P0);
        this.vUp = new Vector(vUp);
        this.vTo = new Vector(vTo);
        this.vRight = this.vTo.crossProduct(this.vUp);
    }
    
    public Camera(Camera obj) {
        this.P0 = obj.getP0();
        this.vUp = obj.getvUp();
        this.vTo = obj.getvTo();
        this.vRight = obj.getvRight();
    }

    public Point3D getP0() {
        return new Point3D(P0);
    }

    public void setP0(Point3D P0) {
        this.P0 = new Point3D(P0);
    }

    public Vector getvUp() {
        return new Vector(vUp);
    }

    public void setvUp(Vector vUp) {
        this.vUp = new Vector(vUp);
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    public Vector getvTo() {
        return new Vector(vTo);
    }

    public void setvTo(Vector vTo) {
        this.vTo = new Vector(vTo);
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    public Vector getvRight() {
        return new Vector(vRight);
    }

    /**
     *
     * @param nX - number of pixels at X plane.
     * @param nY - number of pixels at Y plane.
     * @param x - pixel at X plane.
     * @param y - pixel at Y plane.
     * @param screenDistance - distance between viewer and scene.
     * @param screenWidth - width of the view plane.
     * @param screenHeight - height of the view plane.
     * @return a ray from P0 of the camera(viewer) to a point P(direction calculated as a Vector)
     */
    public Ray constructRayThroughPixel(int nX, int nY, double x, double y, double screenDistance, double screenWidth, double screenHeight){
        //  Image center
        //  Pc = P0 +dVto
        Point3D Pc = this.P0.add(this.vTo.scaling(screenDistance));
        
        //  Vright
        //  Vright = Vto x Vup
        this.vRight = this.vTo.crossProduct(this.vUp);
        
        //  Ratio (pixel width)
        //  Rx = w/#Pixelsx
        //  Ry = h/#Pixelsy
        double Rx = screenWidth / nX;
        double Ry = screenHeight / nY;

        
        //   P = Pc + [[(x - nX/2)*Rx + Rx/2]*Vright - [(y - nY/2)*Ry + Ry/2]*Vup]
        double scalarRight = (x - nX/2.0)*Rx + Rx/2.0;
        double scalarUp = (y - nY/2.0)*Ry + Ry/2.0;
        
        Point3D P = Pc.add(this.vRight.scaling(scalarRight).subtract(this.vUp.scaling(scalarUp)));
        
        Vector direction = new Vector(this.P0, P);
        direction.normalize();
        return new Ray(P, direction);
    }

    @Override
    public String toString() {
        return "Camera{" + "P0=" + P0 + ", vUp=" + vUp + ", vTo=" + vTo + ", vRight=" + vRight + '}';
    }
    
}
