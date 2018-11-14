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
 * Defines a SpotLight illumination.
 * @author arieh_nigri
 */
public class SpotLight extends PointLight {
    
    private Vector direction;

    public SpotLight() {
        super();
        this.direction = new Vector();
    }
    
    public SpotLight(Vector direction, Point3D position, double Kc, double Kl, double Kq, Color color) {
        super(position, Kc, Kl, Kq, color);
        this.direction = new Vector(direction);
    }
    
    public SpotLight(Color color, Point3D position, Vector direction, double Kc, double Kl, double Kq){
        this(direction, position, Kc, Kl, Kq, color);
    }

    public SpotLight(SpotLight obj) {
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
        //IL = [I0 (D • L)] / (Kc + kjd + kqdˆ2)
        
        double d = this.position.distance(point);
        double scalar = this.Kc + this.Kl*d + this.Kq*d*d;
        
        if(scalar == 0.0)
            return Color.BLACK;
        
        //position ---> pointIntersection
        Vector L = this.getL(point);
        
        Vector D = new Vector(this.direction);
        D.normalize();
        
        double angle = D.dotProduct(L);
        
        if(angle <= 0.0)   //   angle >= 90
            return Color.BLACK;
        
        double intensity = angle / scalar;
        
        int r = (int) Math.round(this.color.getRed() * intensity);
        int g = (int) Math.round(this.color.getGreen() * intensity);
        int b = (int) Math.round(this.color.getBlue() * intensity);
        
        if(r > 255)
            r = 255;
        if(g > 255)
            g = 255;
        if(b > 255)
            b = 255;
        
        Color IL = new Color(r, g, b);
        return IL;
    }

}
