/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 * Defines a coordinate in the space, using a double variable.
 *
 * @author arieh_nigri
 */
public class Coordinate implements Comparable<Coordinate> {
    
    private double coordinate;

    // ***************** Constructors ********************** //
    public Coordinate() {
        this.coordinate = 0.0;
    }
    
    public Coordinate(double coordinate) {
        this.coordinate = coordinate;
    }
    
    public Coordinate(Coordinate obj){
        this.coordinate = obj.getCoordinate();
    }

    // ***************** Getters/Setters ********************** //
    public double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double coordinate) {
        this.coordinate = coordinate;
    }
    
    // ***************** Administration  ******************** //
    @Override
    public String toString() {
        return "Coordinate{" + "coordinate=" + coordinate + '}';
    }

    @Override
    public int compareTo(Coordinate coord) {
        
        if(this.coordinate > coord.getCoordinate())
            return 1;
        else if(this.coordinate < coord.getCoordinate())
            return -1;
        else   //   equals
            return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(this.getClass().equals(obj.getClass())))
            return false;
        
        if(obj instanceof Coordinate){
            return this.coordinate == ((Coordinate) obj).getCoordinate();
        }
        else{
            return false;
        }
    }

    
    // ***************** Operations ******************** //

    /**
     *
     * @param coord <b>Coordinate</b> to add to the caller object.
     * @return A new <b>Coordinate</b> from the addition of the caller object with the parameter.
     */
    public Coordinate add(Coordinate coord){
        return new Coordinate(this.coordinate + coord.getCoordinate());
    }
    
    /**
     *
     * @param coord <b>Coordinate</b> to subtract from the caller object.
     * @return A new <b>Coordinate</b> from the subtraction of the caller object with the parameter.
     */
    public Coordinate subtract(Coordinate coord){
        return new Coordinate(this.coordinate - coord.getCoordinate());
    }

}
