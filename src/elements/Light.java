/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;

/**
 * Defines a non-specific Light.
 * @author arieh_nigri
 */
public abstract class Light {
    
    protected Color color;

    public Light() {
        this.color = Color.BLACK;
    }
    
    public Light(Color color) {
        this.color = new Color(color.getRGB());
    }
    
    public Light(Light obj){
        this.color = obj.getColor();
    }
    
    public Color getColor() {
        return new Color(this.color.getRGB());
    }

    public void setColor(Color color) {
        this.color = new Color(color.getRGB());
    }
    
    /**
     * Get the intensity of the light, meaning the Color of it.
     * @return The Color as it is.
     */
    public Color getIntensity(){
        return this.getColor();
    }
    
}