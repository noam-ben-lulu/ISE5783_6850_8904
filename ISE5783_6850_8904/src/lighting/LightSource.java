package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The `LightSource` interface represents a light source in a 3D scene.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light source at the given point.
     *
     * @param p The point in 3D space.
     * @return The intensity of the light, represented as a `Color` object.
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction vector from the light source to the given point.
     *
     * @param p The point in 3D space.
     * @return The direction vector as a `Vector` object.
     */
    public Vector getL(Point p);

    /**
     * Returns the distance between the light source and the given point.
     *
     * @param point The point in 3D space.
     * @return The distance between the light source and the point.
     */
    public double getDistance(Point point);
}
