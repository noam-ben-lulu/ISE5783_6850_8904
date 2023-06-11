/**
 * The Ray class represents a ray in 3D space, defined by an origin point and a direction vector.
 */
package primitives;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Ray {
    /**
     * The origin point of the Ray.
     */
    private final Point p0;

    /**
     * The direction vector of the Ray.
     */
    private final Vector dir;
    private static final double DELTA = 0.01;

    /**
     * Constructs a new Ray object with the given origin point and direction vector.
     *
     * @param point  the origin point of the Ray
     * @param vector the direction vector of the Ray
     */
    public Ray(Point point, Vector vector) {
        p0 = point;
        dir = vector.normalize();
    }
    public Ray(Point point,Vector v,Vector n) {
        dir=v;
        if(v.dotProduct(n)>0)
            p0=point.add(n.scale(DELTA));
        else
            p0=point.subtract(n.scale(DELTA));
    }
    /**
     * Returns the origin point of the Ray.
     *
     * @return the origin point of the Ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the Ray.
     *
     * @return the direction vector of the Ray
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Returns the point on the ray at the given distance t from the origin.
     *
     * @param t the distance from the origin to the point on the ray
     * @return the point on the ray at the given distance t from the origin
     */
    public Point getPoint(double t) {
        if(!(isZero(t*dir.xyz.d1)&&isZero(t*dir.xyz.d2)&&isZero(t*dir.xyz.d3)))
            return p0.add(dir.scale(t));
        return p0;

    }

    /**
     * Returns true if this Ray object is equal to the specified object, and false otherwise.
     *
     * @param o the object to compare to this Ray object
     * @return true if this Ray object is equal to the specified object, and false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Returns a hash code value for the Ray object.
     *
     * @return a hash code value for the Ray object
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * Returns a string representation of the Ray object, in the format "Ray{p0=<p0>, dir=<dir>}".
     *
     * @return a string representation of the Ray object
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * Returns the closest point in the given list of points to the origin of the Ray object.
     *
     * @param points the list of points to find the closest point to the origin of the Ray object
     * @return the closest point in the given list of points to the origin of the Ray object
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    /**
     * Returns the closest GeoPoint in the given list to the origin of the Ray object.
     *
     * @param list the list of GeoPoints to find the closest one to the origin of the Ray object
     * @return the closest GeoPoint in the given list to the origin of the Ray object
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> list) {
        if (list == null)
            return null;
        GeoPoint p = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            if (p0.distance(list.get(i).point) < p0.distance(p.point))
                p = list.get(i);
        }
        return p;
    }
}