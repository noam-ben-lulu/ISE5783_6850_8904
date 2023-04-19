package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry extends Intersectable {
    /**
     * A method that returns the normal vector to the geometry at a specific point
     * @param point A point on the geometry
     * @return The normal vector to the geometry at the given point
     */
    Vector getNormal(Point point);
}