/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * Defines a Ray.
 * @author arieh_nigri
 */
public class Ray implements Comparable<Ray>{
    
    private Point3D POO;
    private Vector direction;
    
    //for homework only
    public static int counter = 0;

    public Ray() {
        this.POO = new Point3D();
        this.direction = new Vector();
    }

    public Ray(Point3D POO, Vector direction) {
        this.POO = new Point3D(POO);
        this.direction = new Vector(direction);
        this.direction.normalize();
        counter++;
    }
    
    public Ray(Vector direction, Point3D POO){
        this(POO, direction);
    }
    
    public Ray(Ray obj){
        this.POO = obj.getPOO();
        this.direction = obj.getDirection();
    }

    public Point3D getPOO() {
        return new Point3D(POO);
    }

    public void setPOO(Point3D POO) {
        this.POO = new Point3D(POO);
    }

    public Vector getDirection() {
        return new Vector(direction);
    }

    public void setDirection(Vector direction) {
        this.direction = new Vector(direction);
        this.direction.normalize();
    }
    
    
    @Override
    public String toString() {
        return "Ray{" + "POO=" + POO + ", direction=" + direction + '}';
    }

    @Override
    public int compareTo(Ray ray) {
        //   using both Point3D and Vector compareTo to compare
        //   3 options for each, we have 9 possible combinations
        if(this.getPOO().compareTo(ray.getPOO()) == 1 && this.getDirection().compareTo(ray.getDirection()) == 1)
            return 1;
        if(this.getPOO().compareTo(ray.getPOO()) == -1 && this.getDirection().compareTo(ray.getDirection()) == -1)
            return -1;
        if(this.getPOO().compareTo(ray.getPOO()) == 0 && this.getDirection().compareTo(ray.getDirection()) == 0)
            return 0;
        if(this.getPOO().compareTo(ray.getPOO()) == 1 && this.getDirection().compareTo(ray.getDirection()) == -1)
            return -1;
        if(this.getPOO().compareTo(ray.getPOO()) == -1 && this.getDirection().compareTo(ray.getDirection()) == 1)
            return 1;
        if(this.getPOO().compareTo(ray.getPOO()) == 1 && this.getDirection().compareTo(ray.getDirection()) == 0)
            return 1;
        if(this.getPOO().compareTo(ray.getPOO()) == 0 && this.getDirection().compareTo(ray.getDirection()) == 1)
            return 1;
        if(this.getPOO().compareTo(ray.getPOO()) == -1 && this.getDirection().compareTo(ray.getDirection()) == 0)
            return -1;
        if(this.getPOO().compareTo(ray.getPOO()) == 0 && this.getDirection().compareTo(ray.getDirection()) == -1)
            return -1;
        else
            return 1;   //   last else, for the function needs a return at the end
    }
    
    public Ray add(Point3D point){
        Coordinate x = this.POO.getX().add(point.getX());
        Coordinate y = this.POO.getY().add(point.getY());
        Coordinate z = this.POO.getZ().add(point.getZ());
        
        Point3D newPOO = new Point3D(x, y, z);
        
        return new Ray(newPOO, this.getDirection());
    }
}
