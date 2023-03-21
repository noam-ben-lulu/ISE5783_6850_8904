package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    // Fields
    private Point q0; // A point in the plane
    private Vector normal; // The normal vector to the plane

    // Constructors
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize(); // Normalize the normal vector
    }

    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p1; // Set q0 to one of the points
        this.normal = null; // Initialize normal to null
    }

    // Methods
    public Vector getNormal(Point p0) {
        return normal; // Return the pre-calculated normal vector
    }

    public Vector getNormal() {
        return normal; // Return the pre-calculated normal vector
    }
}