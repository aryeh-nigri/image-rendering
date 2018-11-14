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
 * Defines a PointLight illumination.
 * @author arieh_nigri
 */
public class PointLight extends Light implements LightSource {
    
    protected Point3D position;
    protected double Kc;
    protected double Kl;
    protected double Kq;

    public PointLight(){
        super();
        this.position = new Point3D();
        this.Kc = 1.0;
        this.Kl = 1.0;
        this.Kq = 1.0;
    }
    
    public PointLight(Point3D position, double Kc, double Kl, double Kq, Color color) {
        super(color);
        this.position = new Point3D(position);
        this.setKc(Kc);
        this.setKl(Kl);
        this.setKq(Kq);
    }
    
    public PointLight(Color color, Point3D position, double Kc, double Kl, double Kq){
        this(position, Kc, Kl, Kq, color);
    }
    
    public PointLight(PointLight obj){
        super(obj);
        this.position = obj.getPosition();
        this.Kc = obj.getKc();
        this.Kl = obj.getKl();
        this.Kq = obj.getKq();
    }

    public Point3D getPosition() {
        return new Point3D(position);
    }

    public void setPosition(Point3D position) {
        this.position = new Point3D(position);
    }

    public double getKc() {
        return Kc;
    }

    public final void setKc(double Kc) {
        if(Kc > 1.0)
            this.Kc = 1.0;
        else if(Kc < 0.0)
            this.Kc = 0.0;
        else
            this.Kc = Kc;
    }

    public double getKl() {
        return Kl;
    }

    public final void setKl(double Kl) {
        if(Kl > 1.0)
            this.Kl = 1.0;
        else if(Kl < 0.0)
            this.Kl = 0.0;
        else
            this.Kl = Kl;
    }

    public double getKq() {
        return Kq;
    }

    public final void setKq(double Kq) {
        if(Kq > 1.0)
            this.Kq = 1.0;
        else if(Kq < 0.0)
            this.Kq = 0.0;
        else
            this.Kq = Kq;
    }

    @Override
    public Color getIntensity(Point3D point) {
        //IL = I0 / (Kc + kl*d + kq*d^2)
        
        double d = this.position.distance(point);
        double scalar = this.Kc + this.Kl*d + this.Kq*d*d;
        
        if(scalar == 0.0)
            return Color.BLACK;
        
        int r = (int) Math.round(this.color.getRed() / scalar);
        int g = (int) Math.round(this.color.getGreen() / scalar);
        int b = (int) Math.round(this.color.getBlue() / scalar);
        
        if(r > 255)
            r = 255;
        if(g > 255)
            g = 255;
        if(b > 255)
            b = 255;
        
        Color IL = new Color(r, g, b);
        return IL;
    }

    @Override
    public Vector getL(Point3D point) {
        //   vector from position to point
        Vector L = new Vector(this.position, point);
        L.normalize();
        return L;
    }
    
}
