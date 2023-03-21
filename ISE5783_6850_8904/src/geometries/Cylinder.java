package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in 3D space.
 * A cylinder is defined by a tube (a circular cylinder) and a height.
 * The height is measured from the base of the cylinder to its top.
 */
public class Cylinder extends Tube {
    /**
     * The height of the cylinder.
     */
    final double height;

    /**
     * Constructs a new Cylinder object with the given radius, axis ray, and height.
     * @param radius the radius of the cylinder (must be a positive value)
     * @param axisRay the axis ray of the cylinder (must not be null)
     * @param height the height of the cylinder (must be a positive value)
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }


    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}