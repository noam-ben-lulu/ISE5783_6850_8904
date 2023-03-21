package geometries;

/**
 * An abstract class representing radial geometries, which have a single radius value.
 */
public abstract class RadialGeometry {
    protected final double radius; // The radius value, protected to allow access by child classes

    /**
     * Constructs a new `RadialGeometry` with the given radius value.
     *
     * @param radius The radius value of the new `RadialGeometry`.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}