/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.awt.Color;
import java.util.List;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Defines a non-specific Geometry.
 * @author Arieh Nigri
 */
public abstract class Geometry {
    
    protected Material material = new Material();
    protected Color emission = Color.BLACK;
    protected int nShininess = 1;
    
    public Geometry(){}
    
    public Geometry(Geometry obj){
        this.material = obj.getMaterial();
        this.emission = obj.getEmission();
        this.nShininess = obj.getnShininess();
    }
    
    public Material getMaterial() {
        return new Material(material);
    }

    public void setMaterial(Material material) {
        this.material = new Material(material);
    }

    public Color getEmission() {
        return new Color(this.emission.getRGB());
    }

    public void setEmission(Color emission) {
        this.emission = new Color(emission.getRGB());
    }

    public int getnShininess() {
        return nShininess;
    }

    public final void setnShininess(int nShininess) {
        if(nShininess <= 0)
            this.nShininess = 0;
        else
            this.nShininess = nShininess;
    }
    
    public void setKs(double ks){
        this.material.setKs(ks);
    }
    
    public void setKd(double kd){
        this.material.setKd(kd);
    }
    
    public void setKr(double kr){
        this.material.setKr(kr);
    }
    
    public void setKt(double kt){
        this.material.setKt(kt);
    }
    
    /**
     * Find all intersections that a Ray makes with a Geometry.
     * @param ray Ray that is shot towards a Geometry.
     * @return List of 3D Points intersected by the given Ray.
     */
    public abstract List<Point3D> findIntersections(Ray ray);
    
    /**
     * Get the normal Vector of a Geometry.
     * @param point 3D Point in a Geometry.
     * @return The normal(perpendicular) Vector of the Geometry, in a specific 3D Point.
     */
    public abstract Vector getNormal(Point3D point);
    
}
