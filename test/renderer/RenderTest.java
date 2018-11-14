/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

/**
 *
 * @author arieh_nigri
 */
public class RenderTest {
    
    public RenderTest() {
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
     * Test of renderImage method, of class Render.
     */
    @Test
    public void testRenderImage() {
        System.out.println("renderImage");
        
        Sphere s = new Sphere(50.0, new Point3D(0.0, 0.0, -150.0));
        s.setEmission(Color.WHITE);
        Triangle t1 = new Triangle(new Point3D(100.0, 0.0, -150.0), new Point3D(0.0, 100.0, -150.0), new Point3D(100.0, 100.0, -150.0));
        t1.setEmission(Color.WHITE);
        Triangle t2 = new Triangle(new Point3D(100.0, 0.0, -150.0), new Point3D(0.0, -100.0, -150.0), new Point3D(100.0, -100.0, -150.0));
        t2.setEmission(Color.WHITE);
        Triangle t3 = new Triangle(new Point3D(-100.0, 0.0, -150.0), new Point3D(0.0, 100.0, -150.0), new Point3D(-100.0, 100.0, -150.0));
        t3.setEmission(Color.WHITE);
        Triangle t4 = new Triangle(new Point3D(-100.0, 0.0, -150.0), new Point3D(0.0, -100.0, -150.0), new Point3D(-100.0, -100.0, -150.0));
        t4.setEmission(Color.WHITE);
        
        Scene scene = new Scene();
        scene.setScreenDistance(149.99);
        ImageWriter im = new ImageWriter("renderTest", 500, 500, 500, 500);
        
        scene.addGeometry(s);
        scene.addGeometry(t1);
        scene.addGeometry(t2);
        scene.addGeometry(t3);
        scene.addGeometry(t4);
        
        Render instance = new Render(scene, im);
        
        instance.renderImage();
        //instance.renderImageAntiAliasing(8);
        instance.printGrid(50);
        instance.writeToImage();
    }
    
    
    /**
     * Test of renderImage method, of class Render, now with emission included in calcColor.
     */
    @Test 
    public void emmissionTest(){
	Scene scene = new Scene();
	scene.setScreenDistance(49.99);
        scene.addGeometry(new Sphere(50, new Point3D(0.0, 0.0, -50.0)));
		
	Triangle triangle = new Triangle(new Point3D(100.0, 0.0, -50.0), new Point3D(0.0, 100.0, -50.0), new Point3D(100.0, 100.0, -50.0));
		
	Triangle triangle2 = new Triangle(new Point3D(100.0, 0.0, -50.0), new Point3D(0.0, -100.0, -50.0), new Point3D(100.0,-100.0, -50.0));
	triangle2.setEmission(new Color (50, 200, 50));
		
	Triangle triangle3 = new Triangle(new Point3D(-100.0, 0.0, -50.0), new Point3D(0.0, 100.0, -50.0), new Point3D(-100.0, 100.0, -50.0));
	triangle3.setEmission(new Color (50, 50, 200));
		
	Triangle triangle4 = new Triangle(new Point3D(-100.0, 0.0, -50.0), new Point3D(0.0, -100.0, -50.0), new Point3D(-100.0, -100.0, -50.0));
	triangle4.setEmission(new Color (200, 50, 50));
		
	scene.addGeometry(triangle);
	scene.addGeometry(triangle2);
	scene.addGeometry(triangle3);
	scene.addGeometry(triangle4);
        	
	ImageWriter imageWriter = new ImageWriter("Emmission test", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(8);
	render.printGrid(50);
	render.writeToImage();
    }
    
    
    /**
     * Test of renderImage method, with a PointLight in the Scene.
     * @see PointLight
     * @see Scene
     */
    @Test
    public void pointLightTest(){
	Scene scene = new Scene();
	Sphere sphere = new Sphere (800.0, new Point3D(0.0, 0.0, -1000.0));
        sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	scene.addGeometry(sphere);
                
	scene.addLight(new PointLight(new Color(255, 100, 100), 
                                      new Point3D(-200.0, -200.0, -100.0), 
                                      0.0, 
                                      0.00001, 
                                      0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Point test", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(4);
	render.writeToImage();
    }
    
    /**
     * Test of renderImage method, with a PointLight in the Scene.
     * @see PointLight
     * @see Scene
     */
    @Test
    public void pointLightTest2(){
		
	Scene scene = new Scene();
	Sphere sphere = new Sphere(800.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
		
	Triangle triangle = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
           				 new Point3D(-3500.0, -3500.0, -1000.0),
                                         new Point3D(3500.0, -3500.0, -2000.0));

	Triangle triangle2 = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
				  	  new Point3D(-3500.0, 3500.0, -1000.0),
				  	  new Point3D(-3500.0, -3500.0, -1000.0));
		
	scene.addGeometry(triangle);
	scene.addGeometry(triangle2);
		
	scene.addLight(new PointLight(new Color(255, 100, 100), 
                                      new Point3D(200.0, 200.0, -100.0), 
                                      0.0, 
                                      0.000001,
                                      0.0000005));
		
	ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(5);
	render.writeToImage();
    }
    
    /**
     * Test of renderImage method, with a SpotLight in the Scene.
     * @see SpotLight
     * @see Scene
     */
    @Test
    public void spotLightTest(){
		
	Scene scene = new Scene();
	Sphere sphere = new Sphere(800.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	scene.addGeometry(sphere);
        
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(-200.0, -200.0, -100.0), 
				     new Vector(2.0, 2.0, -3.0), 
                                     0.0, 
                                     0.00001, 
                                     0.000005));
        
	ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(4);
	render.writeToImage();
    }
    
    @Test
    public void spotLightTestv2(){
        Scene scene = new Scene();
	scene.setScreenDistance(200.0);
	Sphere sphere = new Sphere(500.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	scene.addGeometry(sphere);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(-200.0, -200.0, -150.0), 
                                     new Vector(2.0, 2.0, -3.0), 
                                     0.1, 
                                     0.00001,
                                     0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Spot test v2", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	//render.renderImage();
        render.renderImageAntiAliasing(5);
	render.writeToImage();
    }
    
    /**
     * Test of renderImage method, with a SpotLight in the Scene.
     * @see SpotLight
     * @see Scene
     */
    @Test
    public void spotLightTest2(){
		
	Scene scene = new Scene();
	scene.setScreenDistance(200.0);
	Sphere sphere = new Sphere(500.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	scene.addGeometry(sphere);
		
	Triangle triangle = new Triangle(new Point3D(-125.0, -225.0, -260.0),
					 new Point3D(-225.0, -125.0, -260.0),
					 new Point3D(-225.0, -225.0, -270.0));
		
	triangle.setEmission(new Color(0, 0, 100));
        triangle.setKs(0.3);
        triangle.setKd(0.3);
	triangle.setnShininess(4);
	scene.addGeometry(triangle);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(-200.0, -200.0, -150.0), 
                                     new Vector(2.0, 2.0, -3.0), 
                                     0.1, 
                                     0.00001,
                                     0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(4);
	render.writeToImage();
    }
    
    @Test
    public void spotLightTest2v2(){
		
	Scene scene = new Scene();
	scene.setScreenDistance(200.0);
	Sphere sphere = new Sphere(500.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	scene.addGeometry(sphere);
		
	Triangle triangle = new Triangle(new Point3D(-100.0, -200.0, -260.0),
					 new Point3D(-200.0, -100.0, -260.0),
					 new Point3D(-200.0, -200.0, -270.0));
		
	triangle.setEmission(new Color(0, 0, 100));
        triangle.setKs(0.5);
        triangle.setKd(0.5);
	triangle.setnShininess(4);
	scene.addGeometry(triangle);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(-200.0, -200.0, -150.0), 
                                     new Vector(2.0, 2.0, -3.0), 
                                     0.1, 
                                     0.00001,
                                     0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Spot test 2v2", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	//render.renderImage();
        render.renderImageAntiAliasing(5);
	render.writeToImage();
    }
    
    /**
     * Test of renderImage method, with a SpotLight in the Scene.
     * @see SpotLight
     * @see Scene
     */
    @Test
    public void spotLightTest3(){
		
	Scene scene = new Scene();
		
	Triangle triangle = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
				 	 new Point3D(-3500.0, -3500.0, -1000.0),
				 	 new Point3D(3500.0, -3500.0, -2000.0));

	Triangle triangle2 = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
				  	  new Point3D(-3500.0, 3500.0, -1000.0),
				  	  new Point3D(-3500.0, -3500.0, -1000.0));
		
	scene.addGeometry(triangle);
	scene.addGeometry(triangle2);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(200.0, 200.0, -100.0), 
                                     new Vector(-2.0, -2.0, -3.0),
                                     0.0, 
                                     0.000001, 
                                     0.0000005));
		
	ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(4);
	render.writeToImage();
    }
    
    /**
     *
     */
    @Test
    public void shadowTest(){
		
	Scene scene = new Scene();
	Sphere sphere = new Sphere(500.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
		
	scene.addGeometry(sphere);
		
	Triangle triangle = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
                                         new Point3D(-3500.0, -3500.0, -1000.0),
				 	 new Point3D(3500.0, -3500.0, -2000.0));

	Triangle triangle2 = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
                                          new Point3D(-3500.0, 3500.0, -1000.0),
				  	  new Point3D(-3500.0, -3500.0, -1000.0));
		
	scene.addGeometry(triangle);
	scene.addGeometry(triangle2);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(200.0, 200.0, -100.0), 
				     new Vector(-2.0, -2.0, -3.0), 
                                     0.0, 
                                     0.000001, 
                                     0.0000005));
		
	ImageWriter imageWriter = new ImageWriter("shadow test", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(8);
	render.writeToImage();
    }
    
    
    @Test
    public void shadowTestV2(){
		
	Scene scene = new Scene();
        Sphere sphere = new Sphere(500.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(60);
        sphere.setKs(0.20);
	sphere.setEmission(new Color(0, 0, 100));
        	
	scene.addGeometry(sphere);
		
	Triangle triangle1 = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
					  new Point3D(-3500.0, -3500.0, -1000.0),
				 	  new Point3D(3500.0, -3500.0, -2000.0));

	Triangle triangle2 = new Triangle(new Point3D(3500.0, 3500.0, -2000.0),
				  	  new Point3D(-3500.0, 3500.0, -1000.0),
				  	  new Point3D(-3500.0, -3500.0, -1000.0));
		
	scene.addGeometry(triangle1);
	scene.addGeometry(triangle2);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(200.0, 200.0, -100.0), 
				     new Vector(-2.0, -2.0, -3.0),
                                     0.0, 
                                     0.000001, 
                                     0.0000005));
	
		
	ImageWriter imageWriter = new ImageWriter("Shadow test v2", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	//render.renderImage();
        render.renderImageAntiAliasing(4);
	render.writeToImage();
    }
    
    
    @Test
    public void recursiveTest(){
	Scene scene = new Scene();
	scene.setScreenDistance(300.0);
	
	Sphere sphere = new Sphere(500.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
        sphere.setKt(0.5);
	scene.addGeometry(sphere);
		
	Sphere sphere2 = new Sphere(250.0, new Point3D(0.0, 0.0, -1000.0));
	sphere2.setnShininess(20);
	sphere2.setEmission(new Color(100, 20, 20));
	sphere2.setKt(0.0);
	scene.addGeometry(sphere2);
		
	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(-200.0, -200.0, -150.0), 
                                     new Vector(2.0, 2.0, -3.0),
                                     0.1,
                                     0.00001,
                                     0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Recursive Test 11", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(5);
	render.writeToImage();
    }
    
    @Test
    public void recursiveTest2(){
		
	Scene scene = new Scene();
	scene.setScreenDistance(300.0);
		
	Sphere sphere = new Sphere(300.0, new Point3D(-550.0, -500.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	sphere.setKt(0.5);
	scene.addGeometry(sphere);
		
	Sphere sphere2 = new Sphere(150.0, new Point3D(-550.0, -500.0, -1000.0));
	sphere2.setnShininess(20);
	sphere2.setEmission(new Color(100, 20, 20));
	sphere2.setKt(0.0);
	scene.addGeometry(sphere2);
		
	Triangle triangle = new Triangle(new Point3D(1500.0, -1500.0, -1500.0),
                                         new Point3D(-1500.0, 1500.0, -1500.0),
                                         new Point3D(200.0, 200.0, -375.0));
		
	Triangle triangle2 = new Triangle(new Point3D(1500.0, -1500.0, -1500.0),
                                          new Point3D(-1500.0, 1500.0, -1500.0),
                                          new Point3D(-1500.0, -1500.0, -1500.0));
		
	triangle.setEmission(new Color(20, 20, 20));
	triangle2.setEmission(new Color(20, 20, 20));
	triangle.setKr(1.0);
	triangle2.setKr(0.5);
	scene.addGeometry(triangle);
	scene.addGeometry(triangle2);

	scene.addLight(new SpotLight(new Color(255, 100, 100),
                                     new Point3D(200.0, 200.0, -150.0), 
				     new Vector(-2.0, -2.0, -3.0), 
                                     0.0, 
                                     0.00001,
                                     0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(3);
	render.writeToImage();
    }
	
    @Test
    public void recursiveTest3(){
		
	Scene scene = new Scene();
	scene.setScreenDistance(300);
		
	Sphere sphere = new Sphere(300.0, new Point3D(0.0, 0.0, -1000.0));
	sphere.setnShininess(20);
	sphere.setEmission(new Color(0, 0, 100));
	sphere.setKt(0.5);
	scene.addGeometry(sphere);
		
	Sphere sphere2 = new Sphere(150.0, new Point3D(0.0, 0.0, -1000.0));
	sphere2.setnShininess(20);
	sphere2.setEmission(new Color(100, 20, 20));
	sphere2.setKt(0.0);
	scene.addGeometry(sphere2);
		
	Triangle triangle = new Triangle(new Point3D(2000.0, -1000.0, -1500.0),
                                         new Point3D(-1000.0, 2000.0, -1500.0),
                                         new Point3D(700.0, 700.0, -375.0));
		
	Triangle triangle2 = new Triangle(new Point3D(2000.0, -1000.0, -1500.0),
                                          new Point3D(-1000.0, 2000.0, -1500.0),
                                          new Point3D(-1000.0, -1000.0, -1500.0));
		
	triangle.setEmission(new Color(20, 20, 20));
	triangle2.setEmission(new Color(20, 20, 20));
	triangle.setKr(1.0);
	triangle2.setKr(0.5);
	scene.addGeometry(triangle);
	scene.addGeometry(triangle2);

	scene.addLight(new SpotLight(new Color(255, 100, 100), 
                                     new Point3D(200.0, 200.0, -150.0), 
				     new Vector(-2.0, -2.0, -3.0), 
                                     0.0, 
                                     0.00001,
                                     0.000005));
	
	ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);
		
	Render render = new Render(imageWriter, scene);
		
	render.renderImage();
        //render.renderImageAntiAliasing(4);
	render.writeToImage();
    }
    
    /*
    @Test
    public void allRecursiveTests()
    {
        recursiveTest();
        recursiveTest2();
        recursiveTest3();
    }
    */
    
}
