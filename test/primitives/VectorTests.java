/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.Assert.*;

/**
 *
 * @author Arieh Nigri
 */
public class VectorTests {
    
    public VectorTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of add method, of class Vector.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Coordinate r1 = new Coordinate(4.0);
        Coordinate r2 = new Coordinate(4.0);
        Coordinate r3 = new Coordinate(4.0);
        Vector vec = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        Vector instance = new Vector(new Point3D(z, y, x));   //   (3, 2, 1)
        Vector expResult = new Vector(new Point3D(r1, r2, r3));   //   (4, 4, 4)
        Vector result = instance.add(vec);
        //assertEquals(expResult, result);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of subtract method, of class Vector.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Coordinate r1 = new Coordinate(2.0);
        Coordinate r2 = new Coordinate(0.0);
        Coordinate r3 = new Coordinate(-2.0);
        Vector vec = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        Vector instance = new Vector(new Point3D(z, y, x));   //   (3, 2, 1)
        Vector expResult = new Vector(new Point3D(r1, r2, r3));   //   (2, 0, -2)
        Vector result = instance.subtract(vec);
        //assertEquals(expResult, result);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of dotProduct method, of class Vector.
     */
    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Vector vec = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        Vector instance = new Vector(new Point3D(z, y, x));   //   (3, 2, 1)
        double expResult = 10.0;   //   3*1 + 2*2 + 1*3 = 10
        double result = instance.dotProduct(vec);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of crossProduct method, of class Vector.
     */
    @Test
    public void testCrossProduct() {
        System.out.println("crossProduct");
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Coordinate r1 = new Coordinate(4.0);
        Coordinate r2 = new Coordinate(-8.0);
        Coordinate r3 = new Coordinate(4.0);
        Vector vec = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        Vector instance = new Vector(new Point3D(z, y, x));   //   (3, 2, 1)
        Vector expResult = new Vector(new Point3D(r1, r2, r3));   //   (4, -8, 4)
        Vector result = instance.crossProduct(vec);
        //assertEquals(expResult, result);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of length method, of class Vector.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Vector instance = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        double expResult = Math.sqrt(14.0);
        double result = instance.length();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of scaling method, of class Vector.
     */
    @Test
    public void testScaling() {
        System.out.println("scaling");
        double scalar = 2.5;
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Coordinate r1 = new Coordinate(2.5);
        Coordinate r2 = new Coordinate(5.0);
        Coordinate r3 = new Coordinate(7.5);
        Vector instance = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        Vector expResult = new Vector(new Point3D(r1, r2, r3));   //   (2.5, 5.0, 7.5)
        Vector result = instance.scaling(scalar);
        //assertEquals(expResult, result);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of normalize method, of class Vector.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");        
        Coordinate x = new Coordinate(1.0);
        Coordinate y = new Coordinate(2.0);
        Coordinate z = new Coordinate(3.0);
        Vector instance = new Vector(new Point3D(x, y, z));   //   (1, 2, 3)
        Vector vec = instance.scaling(3.0);
        
        instance.normalize();
        vec.normalize();
        
        assertEquals(instance.toString(), vec.toString());
        
        Vector v = new Vector(3.5,-5.0,10.0);
        v.normalize();
        assertEquals("", 1, v.length(),1e-10);
    }
    /**
     * Test of distance method, of class Point3D
     */
    @Test
    public void testDistance(){
        System.out.println("distance");
        Point3D p1 = new Point3D();
        Point3D p2 = new Point3D(1.0, 2.0, 2.0);
        Point3D p3 = new Point3D(2.0, 4.0, 4.0);
        
        double d1 = p2.distance(p1);   //   should be 3.0
        double d2 = p3.distance(p3);   //   should be 0.0
        double d3 = p3.distance(p2);   //   should be 3.0
        double d4 = p2.distance(p3);   //   should be same as d3
        
        if(d1 != 3.0){
            fail("d1 failed!");
        }
        if(d2 != 0.0){
            fail("d2 failed!");
        }
        if(d3 != 3.0){
            fail("d3 failed!");
        }
        if(d4 != d3){
            fail("d4 failed");
        }
        
    }
    
}
