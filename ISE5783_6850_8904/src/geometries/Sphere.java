package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere class represents a 3D sphere in Cartesian coordinate system.
 * A sphere is defined by its center point and its radius length.
 * A sphere is a radial geometry, so it extends the RadialGeometry abstract class.
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere.
     */
    final private Point center;
    /**
     * The radius length of the sphere.
     * This field overrides the "radius" field in the RadialGeometry class.
     */


    /**
     * Sphere constructor.
     * Constructs a new sphere with the specified radius and center point.
     * @param radius The radius length of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Retrieves the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Retrieves the radius length of the sphere.
     * @return The radius length of the sphere.
     */
    public double getRadius() {
        return radius;
    }
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center);
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
