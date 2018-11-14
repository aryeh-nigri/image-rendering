/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Defines a Directional illumination, like the Sun.
 * @author arieh_nigri
 */
public class DirectionalLight extends Light implements LightSource {
    
    private Vector direction;

    public DirectionalLight(){
        super();
        this.direction = new Vector();
    }
    
    public DirectionalLight(Vector direction, Color color) {
        super(color);
        this.direction = new Vector(direction);
    }
    
    public DirectionalLight(Color color, Vector direction) {
        this(direction, color);
    }
    
    public DirectionalLight(DirectionalLight obj){
        super(obj);
        this.direction = obj.getDirection();
    }

    public Vector getDirection() {
        return new Vector(direction);
    }

    public void setDirection(Vector direction) {
        this.direction = new Vector(direction);
    }

    @Override
    public Color getIntensity(Point3D point) {
        //IL = I0
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point3D point) {
        Vector L = new Vector(this.direction);
        L.normalize();
        return L;
    }
    
}
