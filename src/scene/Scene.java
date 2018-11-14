/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Defines a Scene, to be viewed by some viewer.
 * @author arieh_nigri
 */
public class Scene {
    
    private String sceneName;
    private Color background;
    private AmbientLight ambientLight;
    private List<Geometry> geometries;
    private List<LightSource> lights;
    private Camera camera;
    private double screenDistance;

    
    public Scene() {
        this.background = Color.BLACK;
        this.screenDistance = 100.0;
        this.geometries = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.sceneName = "default";
        this.ambientLight = new AmbientLight();
        this.camera = new Camera();
    }
    
    public Scene(AmbientLight aLight, Color background, Camera camera, double screenDistance){
        this();
        this.ambientLight = new AmbientLight(aLight);
        this.background = new Color(background.getRGB());
        this.camera = new Camera(camera);
        this.screenDistance = screenDistance;
    }
    
    public Scene(String sceneName, Color background, AmbientLight ambientLight, Camera camera, double screenDistance) {
        this(ambientLight, background, camera, screenDistance);
        this.sceneName = sceneName;
    }
        
    public Scene(String sceneName, Color background, AmbientLight ambientLight, List<Geometry> geometries, List<LightSource> lights, Camera camera, double screenDistance) {
        this(sceneName, background, ambientLight, camera, screenDistance);
        this.geometries = new ArrayList<>(geometries);
        this.lights = new ArrayList<>(lights);
    }
    
    public Scene(Scene obj){
        this.background = obj.getBackground();
        this.screenDistance = obj.getScreenDistance();
        this.geometries = obj.getGeometries();
        this.lights = obj.getLights();
        this.sceneName = obj.getSceneName();
        this.ambientLight = obj.getAmbientLight();
        this.camera = obj.getCamera();
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Color getBackground() {
        return new Color(background.getRGB());
    }

    public void setBackground(Color background) {
        this.background = new Color(background.getRGB());
    }

    public AmbientLight getAmbientLight() {
        return new AmbientLight(ambientLight);
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = new AmbientLight(ambientLight);
    }

    public List<Geometry> getGeometries() {
        return new ArrayList<>(geometries);
    }

    public void setGeometries(List<Geometry> geometries) {
        this.geometries = new ArrayList<>(geometries);
    }

    public List<LightSource> getLights() {
        return new ArrayList<>(lights);
    }

    public void setLights(List<LightSource> lights) {
        this.lights = new ArrayList<>(lights);
    }
    
    public Camera getCamera() {
        return new Camera(camera);
    }

    public void setCamera(Camera camera) {
        this.camera = new Camera(camera);
    }

    public double getScreenDistance() {
        return screenDistance;
    }

    public void setScreenDistance(double screenDistance) {
        this.screenDistance = screenDistance;
    }
    
    public void addGeometry(Geometry g){
        this.geometries.add(g);
    }
    
    public void addLight(LightSource l){
        this.lights.add(l);
    }
    
    public Iterator<Geometry> getGeometriesIterator(){
        return this.geometries.iterator();
    }
    
    public Iterator<LightSource> getLightsIterator(){
        return this.lights.iterator();
    }
    
    public void clearGeometries(){
        this.geometries.clear();
    }
    
    public void clearLights(){
        this.lights.clear();
    }

    @Override
    public String toString() {
        return "Scene{" + "sceneName=" + sceneName + ", background=" + background + ", ambientLight=" + ambientLight + ", geometries=" + geometries + ", camera=" + camera + ", screenDistance=" + screenDistance + '}';
    }
    
}
