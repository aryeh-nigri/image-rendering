/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;

/**
 * Defines the Ambient illumination.
 * @author arieh_nigri
 */
public class AmbientLight extends Light {
    
    private final double Ka = 0.1;

    public AmbientLight() {
        super();
    }
    
    public AmbientLight(Color color) {
        super(color);
    }
    
    public AmbientLight(int r, int g, int b){
        this(new Color(r, g, b));
    }
    
    public AmbientLight(AmbientLight obj){
        super(obj);
    }
    
    //public AmbientLight(Map<String, String> attributes){}

    public double getKa() {
        return Ka;
    }

    
    @Override
    public Color getIntensity()
    {   //Ipoint3D = KAM * IAM
        int r = (int) Math.round(this.color.getRed() * this.Ka);
        int g = (int) Math.round(this.color.getGreen() * this.Ka);
        int b = (int) Math.round(this.color.getBlue() * this.Ka);
        
        Color Ipoint3D = new Color(r, g, b);
        return Ipoint3D;
    }

    @Override
    public String toString() {
        return "AmbientLight{" + "color=" + this.getColor() + ", Ka=" + Ka + '}';
    }
      
}