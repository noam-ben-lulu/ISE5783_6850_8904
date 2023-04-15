package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents a tube in 3D Cartesian coordinate system.
 * It extends the RadialGeometry class.
 * A tube is defined by its radius and a Ray that represents the axis of the tube.
 */
public class Tube extends RadialGeometry {
    /**
     * The axis of the tube.
     */
    final protected Ray axisRay;

    /**
     * Constructs a new tube with the given radius and axis.
     * @param radius The radius of the tube.
     * @param axisRay The axis of the tube.
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Gets the axis of the tube.
     * @return The axis of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        if(t!=0)
            return   p.subtract(axisRay.getP0().add(axisRay.getDir().scale(t))).normalize();
        return  p.subtract(axisRay.getP0());
    }
}