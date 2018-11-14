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
 * Defines a non-specific LightSource.
 * @author arieh_nigri
 */
public interface LightSource {
    
    /**
     *
     * @param point The 3D Point illuminated by the LightSource.
     * @return The resulted Color when a LightSource lights a given 3D Point.
     */
    public Color getIntensity(Point3D point);
    
    /**
     * LightSource to Point vector.
     * @param point Point illuminated by the LightSource.
     * @return The Vector from the LightSource to the specified point.
     */
    public Vector getL(Point3D point);
}
