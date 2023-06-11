package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The `PointLight` class represents a point light source in a 3D scene.
 * It extends the `Light` class and implements the `LightSource` interface.
 */
public class PointLight extends Light implements LightSource {

    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * Constructs a `PointLight` object with the given intensity and position.
     *
     * @param intensity The intensity of the point light, represented as a `Color` object.
     * @param position  The position of the light source, represented as a `Point` object.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor (kC) for the point light.
     *
     * @param kC The constant attenuation factor.
     * @return The updated `PointLight` object.
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor (kL) for the point light.
     *
     * @param kL The linear attenuation factor.
     * @return The updated `PointLight` object.
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor (kQ) for the point light.
     *
     * @param kQ The quadratic attenuation factor.
     * @return The updated `PointLight` object.
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Returns the intensity of the light source at the given point.
     *
     * @param p The point in 3D space.
     * @return The intensity of the light, represented as a `Color` object.
     */
    @Override
    public Color getIntensity(Point p) {
        double D = p.distance(position);
        return super.getIntensity().scale(1 / (kC + kL * D + kQ * D * D));
    }

    /**
     * Returns the direction vector from the light source to the given point.
     *
     * @param p The point in 3D space.
     * @return The direction vector as a `Vector` object.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    /**
     * Returns the distance between the light source and the given point.
     *
     * @param point The point in 3D space.
     * @return The distance between the light source and the point.
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(this.position);
    }
}
