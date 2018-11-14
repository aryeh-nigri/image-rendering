/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import elements.Camera;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * @author Arieh Nigri
 */
public class SphereTest {
    
    public SphereTest() {
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
     * Test of findIntersections method, of class Sphere.
     */
    @Test
    public void testFindIntersections() {
        System.out.println("findIntersections");
        
        Sphere s1 = new Sphere(new Point3D(0, 0, -3), 1.0);
        Sphere s2 = new Sphere(new Point3D(0, 0, -3), 10.0);
        int count1 = 0;
        int count2 = 0;
        int expCount1 = 2;
        int expCount2 = 9;
        Camera cam = new Camera(new Point3D(), new Vector(0, 1, 0), new Vector(0, 0, -1));
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                Ray ray = cam.constructRayThroughPixel(3, 3, i, j, 1.0, 9.0, 9.0);
                List<Point3D> points1 = s1.findIntersections(ray);
                List<Point3D> points2 = s2.findIntersections(ray);
                System.out.println("points1 at (" + i + ", " + j +"): " + points1);
                System.out.println("points2 at (" + i + ", " + j +"): " + points2);
                count1 += points1.size();
                count2 += points2.size();
            }//inner for
        }//outer for
        
        if(count1 != expCount1)
            fail("First test failed!");
        else{
            System.out.println("First test succeeded");
        }
        
        if(count2 != expCount2)
            fail("Second test failed!");
        else{
            System.out.println("Second test succeeded");
        }
        
    }
    
}
