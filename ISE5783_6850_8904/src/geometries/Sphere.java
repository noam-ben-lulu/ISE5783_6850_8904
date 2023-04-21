package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.sqrt;

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
        Vector u;
        double tm=0,d=0;
        if (!center.equals(ray.getP0())) {
            u = this.center.subtract(ray.getP0());
            tm = ray.getDir().dotProduct(u);
            d = Util.alignZero(sqrt(u.lengthSquared() - (tm * tm)));
        }
        if (d >= this.radius)
            return null;
        double th = Util.alignZero(sqrt((radius * radius) - (d * d)));
        double t1 = Util.alignZero(tm + th);
        double t2 = Util.alignZero(tm - th);

        Point p1;
        Point p2;
        if(t1>0)
        {
            p1 = ray.getPoint(t1);
            if (t2 > 0) {
                p2 = ray.getPoint(t2);
                return List.of(p1, p2);
            }
            return List.of(p1);
        }
        if(t2>0)
        {
            p2 = ray.getPoint(t2);
            return List.of(p2);
        }
        return null;


    }
}