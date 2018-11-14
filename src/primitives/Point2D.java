/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * Defines a 2D Point.
 * @author arieh_nigri
 */
public class Point2D implements Comparable<Point2D>{
    
    protected Coordinate x;
    protected Coordinate y;

    // ***************** Constructors ********************** //
    public Point2D() {
        this.x = new Coordinate();
        this.y = new Coordinate();
    }

    public Point2D(Coordinate x, Coordinate y) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
    }
    
    public Point2D(double u, double v){
        this.x = new Coordinate(u);
        this.y = new Coordinate(v);
    }
    
    public Point2D(Point2D obj){
        this.x = obj.getX();
        this.y = obj.getY();
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return new Coordinate(x);
    }

    public void setX(Coordinate x) {
        this.x = new Coordinate(x);
    }

    public Coordinate getY() {
        return new Coordinate(y);
    }

    public void setY(Coordinate y) {
        this.y = new Coordinate(y);
    }

    @Override
    public String toString() {
        return "Point2D{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public int compareTo(Point2D point) {
        //   the comparison between 2D points is made by comparing each
        //   coordinate, in a sequence of Y and then X
        if(this.getY().getCoordinate() > point.getY().getCoordinate())
            return 1;
        else if(this.getY().getCoordinate() < point.getY().getCoordinate())
            return -1;
        else{   //   the Y coordinate is equal
            if(this.getX().getCoordinate() > point.getX().getCoordinate())
                return 1;
            else if(this.getX().getCoordinate() < point.getX().getCoordinate())
                return -1;
            else   //   the X coordinate is also equal
                return 0;
        }
    }
    
}
