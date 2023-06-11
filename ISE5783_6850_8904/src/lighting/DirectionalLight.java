package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The `DirectionalLight` class represents a directional light source in a 3D scene.
 * It extends the `Light` class and implements the `LightSource` interface.
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a `DirectionalLight` object with the given intensity and direction.
     *
     * @param intensity The intensity of the directional light, represented as a `Color` object.
     * @param direction The direction of the light, represented as a `Vector` object.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Returns the intensity of the light source at the given point.
     *
     * @param p The point in 3D space.
     * @return The intensity of the light, represented as a `Color` object.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * Returns the direction vector from the light source to the given point.
     *
     * @param p The point in 3D space.
     * @return The direction vector as a `Vector` object.
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

    /**
     * Returns the distance between the light source and the given point.
     *
     * @param point The point in 3D space.
     * @return The distance between the light source and the point.
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
