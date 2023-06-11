package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The Scene class represents a scene in a ray tracing application.
 * It contains information about the geometries, lights, ambient light, and background color of the scene.
 */
public class Scene {

    public Geometries geometries;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Color background = Color.BLACK;
    public String sceneName;
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a Scene object with the given name.
     *
     * @param sceneName the name of the scene
     */
    public Scene(String sceneName) {
        this.sceneName = sceneName;
        this.geometries = new Geometries();
    }

    /**
     * Sets the geometries of the scene.
     *
     * @param geometries the geometries to set
     * @return the updated Scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight the ambient light to set
     * @return the updated Scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the background color of the scene.
     *
     * @param background the background color to set
     * @return the updated Scene object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the lights in the scene.
     *
     * @param lights the lights to set
     * @return the updated Scene object
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
