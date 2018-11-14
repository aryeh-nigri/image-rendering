/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

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
public class CameraTest {
    
    public CameraTest() {
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
     * Test of constructRayThroughPixel method, of class Camera.
     */
    @Test
    public void testConstructRayThroughPixel() {
        System.out.println("constructRayThroughPixel");
        
        Camera instance = new Camera(new Point3D(), new Vector(0, 1, 0), new Vector(0, 0, -1));
        
        for(int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                Ray ray = instance.constructRayThroughPixel(3, 3, i, j, 1.0, 9.0, 9.0);
                
                double x = ray.getPOO().getX().getCoordinate();
                double y = ray.getPOO().getY().getCoordinate();
                double z = ray.getPOO().getZ().getCoordinate();
                
                if(z == -1.0)
                {
                    if(x == 3.0 || x == 0.0 || x == -3.0)
                    {
                        if(y == 3.0 || y == 0.0 || y == -3.0)
                        {
                            System.out.println("Ray at accepted point: " + ray.getPOO().toString());
                        }
                        else
                        {
                            fail("Y out of range: " + ray.getPOO().toString());
                        }
                    }
                    else
                    {
                        fail("X out of range: " + ray.getPOO().toString());
                    }
                }
                else
                {
                    fail("Z out of range: " + ray.getPOO().toString());
                }
                
                
            }//inner for
        }//outer for
        
    }
    
}
