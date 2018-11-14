/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * Defines a 3D Point.
 * @author arieh_nigri
 */
public class Point3D extends Point2D{
    
    private Coordinate z;

    public Point3D() {
        super();
        this.z = new Coordinate();
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.z = new Coordinate(z);
    }
    
    public Point3D(double u, double v, double w){
        super(u, v);
        this.z = new Coordinate(w);
    }
    
    public Point3D(Point3D obj){
        super(obj);
        this.z = obj.getZ();
    }

    public Coordinate getZ() {
        return new Coordinate(z);
    }

    public void setZ(Coordinate z) {
        this.z = new Coordinate(z);
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    @Override
    public int compareTo(Point2D p) {
        //   the comparison between 3D points is made by comparing each
        //   coordinate, in a sequence of Y and then X and then Z
        Point3D point = (Point3D)p;
        
        if(this.getY().getCoordinate() > point.getY().getCoordinate())
            return 1;
        else if(this.getY().getCoordinate() < point.getY().getCoordinate())
            return -1;
        else{   //   the Y coordinate is equal
            if(this.getX().getCoordinate() > point.getX().getCoordinate())
                return 1;
            else if(this.getX().getCoordinate() < point.getX().getCoordinate())
                return -1;
            else{   //   the X coordinate is also equal
                if(this.getZ().getCoordinate() > point.getZ().getCoordinate())
                    return 1;
                else if(this.getZ().getCoordinate() < point.getZ().getCoordinate())
                    return -1;
                else   //   the Z coordinate is also equal, making the 2 points equal
                    return 0;
            }
        }

    }
    
    /**
     *
     * @param point The 3D Point to calculate the distance to.
     * @return The distance between 2 Points 3D.
     */
    public double distance(Point3D point){
        //   d = sqrt( (x1-x2)^2 + (y1 - y2)^2 + (z1 - z2)^2 )
        double x0 = Math.pow(this.getX().getCoordinate() - point.getX().getCoordinate(), 2);
        double y0 = Math.pow(this.getY().getCoordinate() - point.getY().getCoordinate(), 2);
        double z0 = Math.pow(this.getZ().getCoordinate() - point.getZ().getCoordinate(), 2);
        
        return Math.sqrt(x0 + y0 + z0);
    }
    
    public Point3D add(Vector vec){
        
        Point3D point = new Point3D(this.getX().add(vec.getHead().getX()),
                                        this.getY().add(vec.getHead().getY()),
                                        this.getZ().add(vec.getHead().getZ()));
        return point;
    }
    
    public Point3D subtract(Vector vec){
        
        Point3D point = new Point3D(this.getX().subtract(vec.getHead().getX()),
                                        this.getY().subtract(vec.getHead().getY()),
                                        this.getZ().subtract(vec.getHead().getZ()));
        return point;
    }
    
}
