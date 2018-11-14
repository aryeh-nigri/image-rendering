/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

/**
 * Defines a circular Geometry, with a radius value.
 * @author Arieh Nigri
 */
public abstract class RadialGeometry extends Geometry {
    
    protected double radius;
    
    public RadialGeometry() {
        this.radius = 0.0;
    }
    
    public RadialGeometry(double radius){
        this.setRadius(radius);
    }
    
    public RadialGeometry(RadialGeometry obj){
        super(obj);
        this.radius = obj.getRadius();
    }
    
    public double getRadius() {
        return radius;
    }

    public final void setRadius(double radius) {
        if(radius < 0.0)
            this.radius = 0.0;
        else
            this.radius = radius;
    }
    
}
