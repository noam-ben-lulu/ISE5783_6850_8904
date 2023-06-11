package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The `SpotLight` class represents a spot light source in a 3D scene.
 * It extends the `PointLight` class.
 */
public class SpotLight extends PointLight {

    private Vector direction;

    /**
     * Constructs a `SpotLight` object with the given intensity, position, and direction.
     *
     * @param intensity  The intensity of the spot light, represented as a `Color` object.
     * @param position   The position of the light source, represented as a `Point` object.
     * @param direction  The direction of the spot light, represented as a `Vector` object.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    /**
     * Calculates the intensity of the spot light at the given point.
     * The intensity is adjusted based on the angle between the light direction and the direction to the point.
     *
     * @param p The point in 3D space.
     * @return The intensity of the light at the given point, represented as a `Color` object.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0, getL(p).dotProduct(this.direction.normalize())));
    }

    /**
     * Returns the direction vector from the spot light source to the given point.
     *
     * @param p The point in 3D space.
     * @return The direction vector from the spot light source to the given point, represented as a `Vector` object.
     */
    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

}
