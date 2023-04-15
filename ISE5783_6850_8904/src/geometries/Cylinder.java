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
            double t=0;
            if (!point.equals(axisRay.getP0()))
                 t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
            if (t == this.height) // if is on base B return the dir
                return axisRay.getDir();
            else if (t == 0) // if is on base A return the opposite of dir
                return axisRay.getDir().scale(-1);
            else
                return super.getNormal(point);// if is not on the bases then return get normal of tube

    }
}