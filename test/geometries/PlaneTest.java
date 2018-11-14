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
public class PlaneTest {
    
    public PlaneTest() {
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
     * Test of findIntersections method, of class Plane.
     */
    @Test
    public void testFindIntersections() {
        System.out.println("findIntersections");
        
        Plane p1 = new Plane(new Vector(0, 0, -1), new Point3D(0, 0, -3));
        Plane p2 = new Plane(new Vector(0, 0.25, -1.0), new Point3D(0, 0, -3));
        int count1 = 0;
        int count2 = 0;
        int expCount1 = 9;
        int expCount2 = 9;
        Camera cam = new Camera(new Point3D(), new Vector(0, 1, 0), new Vector(0, 0, -1));
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                Ray r = cam.constructRayThroughPixel(3, 3, i, j, 1.0, 9.0, 9.0);
                List<Point3D> exp1 = p1.findIntersections(r);
                List<Point3D> exp2 = p2.findIntersections(r);
                System.out.println("point1 at (" + i + ", " + j + "): " + exp1);
                count1 += exp1.size();
                System.out.println("point2 at (" + i + ", " + j + "): " + exp2);
                count2 += exp2.size();
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
