/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * Defines a Vector.
 * @author arieh_nigri
 */
public class Vector implements Comparable<Vector>{
    
    private Point3D head;

    public Vector() {
        this.head = new Point3D();
    }

    public Vector(Point3D head) {
        this.head = new Point3D(head);
    }
    
    /**
     * Creates a Vector from start point to end point.
     * @param start Starting point of Vector.
     * @param end Ending point of Vector.
     */
    public Vector(Point3D start, Point3D end){
        Vector v = new Vector(start);
        Point3D p = end.subtract(v);
        this.head = new Point3D(p);
    }
    
    public Vector(double u, double v, double w){
        Coordinate x = new Coordinate(u);
        Coordinate y = new Coordinate(v);
        Coordinate z = new Coordinate(w);
        
        this.head = new Point3D(x, y, z);
    }

    public Vector(Vector obj){
        this.head = obj.getHead();
    }
    
    public Point3D getHead() {
        return new Point3D(head);
    }

    public void setHead(Point3D head) {
        this.head = new Point3D(head);
    }

    @Override
    public String toString() {
        return "Vector{" + "head=" + head + '}';
    }

    @Override
    public int compareTo(Vector vec) {
        //   the comparison will verify each direction
        //   in the order of Y, X, Z.
        //   after normalization, every length will be equal, 1,
        //   so compare length is meanless
        if(this.getHead().getY().getCoordinate() > vec.getHead().getY().getCoordinate())
            return 1;
        else if(this.getHead().getY().getCoordinate() < vec.getHead().getY().getCoordinate())
            return -1;
        else{   //   Y is equal
            if(this.getHead().getX().getCoordinate() > vec.getHead().getX().getCoordinate())
                return 1;
            else if(this.getHead().getX().getCoordinate() < vec.getHead().getX().getCoordinate())
                return -1;
            else{   //   X is equal
                if(this.getHead().getZ().getCoordinate() > vec.getHead().getZ().getCoordinate())
                    return 1;
                else if(this.getHead().getZ().getCoordinate() < vec.getHead().getZ().getCoordinate())
                    return -1;
                else   //   Z is equal   --->   same Point3D, same Vector
                    return 0;
            }
        }
        
    }
    
    /**
     * Add 2 Vectors.
     * @param vec Vector to be added.
     * @return A new Vector that is the addition.
     */
    public Vector add(Vector vec){
        
        Point3D addedHead = new Point3D(this.getHead().getX().add(vec.getHead().getX()),
                                        this.getHead().getY().add(vec.getHead().getY()),
                                        this.getHead().getZ().add(vec.getHead().getZ()));
        
        return new Vector(addedHead);
    }
    
    /**
     * Subtract 2 Vectors.
     * @param vec Vector to be subtracted.
     * @return A new Vector that is the subtraction.
     */
    public Vector subtract(Vector vec){
        return this.add(vec.scaling(-1.0));
    }
    
    /**
     * Calculates the dot product(•) between 2 <code>Vectors</code>.
     * @param vec Second Vector for the operation.
     * @return The dot product(scalar product) between 2 Vectors.
     */
    public double dotProduct(Vector vec){
        //   u*v = u1*v1 + u2*v2 + u3*v3   ;   a scalar is returned
        double u1v1 = this.getHead().getX().getCoordinate() * vec.getHead().getX().getCoordinate();
        double u2v2 = this.getHead().getY().getCoordinate() * vec.getHead().getY().getCoordinate();
        double u3v3 = this.getHead().getZ().getCoordinate() * vec.getHead().getZ().getCoordinate();
        
        return u1v1 + u2v2 + u3v3;
    }
    
    /**
     *
     * @param vec Second Vector for the operation.
     * @return The cross product(vector product) between 2 Vectors.
     */
    public Vector crossProduct(Vector vec){
        //   u = u1 + u2 + u3   ;   v = v1 + v2 + v3
        //   u X v = (u2*v3 - u3*v2)i + (u3*v1 - u1*v3)j + (u1*v2 - u2*v1)k   ;   a new Vector is returned
        Coordinate x = new Coordinate(this.getHead().getY().getCoordinate() * vec.getHead().getZ().getCoordinate()
                                      - this.getHead().getZ().getCoordinate() * vec.getHead().getY().getCoordinate());
        Coordinate y = new Coordinate(this.getHead().getZ().getCoordinate() * vec.getHead().getX().getCoordinate()
                                      - this.getHead().getX().getCoordinate() * vec.getHead().getZ().getCoordinate());
        Coordinate z = new Coordinate(this.getHead().getX().getCoordinate() * vec.getHead().getY().getCoordinate()
                                      - this.getHead().getY().getCoordinate() * vec.getHead().getX().getCoordinate());
        
        return new Vector(new Point3D(x, y, z));
    }
    
    /**
     *
     * @return The length of the current Vector.
     */
    public double length(){   //   magnitude
        //   |v| = sqrt(v1^2 + v2^2 + v3^2)
        double v1 = Math.pow(this.getHead().getX().getCoordinate(), 2);
        double v2 = Math.pow(this.getHead().getY().getCoordinate(), 2);
        double v3 = Math.pow(this.getHead().getZ().getCoordinate(), 2);
        
        return Math.sqrt(v1 + v2 + v3);
    }
    
    /**
     *
     * @param scalar Value to multiply the current Vector.
     * @return The Vector multiplied by a given value.
     */
    public Vector scaling(double scalar){
        //   v * a = (v1*a)i + (v2*a)j + (v3*a)k
        Coordinate x = new Coordinate(this.getHead().getX().getCoordinate() * scalar);
        Coordinate y = new Coordinate(this.getHead().getY().getCoordinate() * scalar);
        Coordinate z = new Coordinate(this.getHead().getZ().getCoordinate() * scalar);
        
        return new Vector(new Point3D(x, y, z));
    }
    
    /**
     *
     * Normalizes the current Vector. 
     * CAUTION: This method changes the object itself!
     */
    public void normalize() {
        //   the normal Vector is the Vector itself divided by its length
        //   making a new Vector with a length of 1
        double len = this.length();
        
        if(len != 0.0 || len != 1.0){   //   dont calculate if it is not necessary
            Coordinate normal_X = new Coordinate(this.getHead().getX().getCoordinate() / len);
            Coordinate normal_Y = new Coordinate(this.getHead().getY().getCoordinate() / len);
            Coordinate normal_Z = new Coordinate(this.getHead().getZ().getCoordinate() / len);
            
            Point3D normal_head = new Point3D(normal_X, normal_Y, normal_Z);
            
            this.setHead(normal_head);
        }
        
    }
    
    
}
