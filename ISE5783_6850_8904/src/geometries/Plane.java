package geometries;

import primitives.Point;
import primitives.Util;
import primitives.Vector;
import primitives.Ray;

import java.util.List;
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
        Vector v1= p2.subtract(p1);
        Vector v2=p3.subtract(p1);
        this.normal = (v1.crossProduct(v2)).normalize(); // Initialize normal to null
    }

    // Methods
    public Vector getNormal(Point p0) {
        return normal; // Return the pre-calculated normal vector
    }

    public Vector getNormal() {
        return normal; // Return the pre-calculated normal vector
    }
    @Override
    public List<Point> findIntsersections(Ray ray) {
        //   if (ray.getDir()==new Vector(0,0,0))
        //     throw new IllegalArgumentException("The direction of the ray is a zero vector");
        double nv = normal.dotProduct(ray.getDir());
        if (Util.isZero(nv))
            return null;
        if(q0.equals(ray.getP0()))
            return null;
        double t= Util.alignZero(normal.dotProduct(q0.subtract(ray.getP0())))/nv;
        if(t<=0)
            return null;
        return  List.of(ray.getPoint(t));


    }
}