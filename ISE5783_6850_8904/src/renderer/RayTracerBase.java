/**
 * The RayTracerBase class is an abstract class that serves as a base for ray tracing algorithms. It defines the
 * basic structure of a ray tracer and provides a common interface for tracing rays.
 */
package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

public abstract class RayTracerBase {

    /**
     * The scene to be rendered.
     */
    protected Scene scene;

    /**
     * Constructs a new RayTracerBase object with the given scene.
     *
     * @param scene the scene to be rendered
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces the given ray and returns the color of the closest intersection between the ray and the scene's geometries.
     *
     * @param ray the ray to be traced
     * @return the color of the closest intersection between the ray and the scene's geometries
     */
    public abstract Color traceRay(Ray ray);
    public abstract  Color traceBeamRay(List<Ray> beam);
    //public abstract Color performAdaptiveSuperSampling(Point centerPoint, double pixelWidth, double pixelHeight, double minSubdivisionWidth, double minSubdivisionHeight, Point cameraLocation, Vector rightVector, Vector upVector, List<Point> previousPoints);
}
