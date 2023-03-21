package geometries;

import primitives.Point;
/**
 * Triangle class represents a 3D triangle in Cartesian coordinate system.
 * A triangle is defined by its three vertices.
 * A triangle is a polygon, so it extends the Polygon class.
 */
public class Triangle extends Polygon {
    /**
     @param p1 @param p2 @param p3 Constructs a new triangle with the specified points.**/
    public Triangle(Point p1,Point p2,Point p3) {
        super(p1,p2,p3);
    }
}