package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static primitives.Util.isZero;

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

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<Point> list = this.plane.findIntersections(ray);
        // Point p=list.get(0);
        if(list!=null)
        {
            Vector v1 = this.vertices.get(0).subtract(ray.getP0());
            Vector v2 = this.vertices.get(1).subtract(ray.getP0());
            Vector v3 = this.vertices.get(2).subtract(ray.getP0());
            Vector n1 = v1.crossProduct(v2);
            Vector n2 = v2.crossProduct(v3);
            Vector n3 = v3.crossProduct(v1);
            double t1=ray.getDir().dotProduct(n1);
            double t2=ray.getDir().dotProduct(n2);
            double t3=ray.getDir().dotProduct(n3);
            if(isZero(ray.getDir().dotProduct(v1))||isZero(ray.getDir().dotProduct(v2))||isZero(ray.getDir().dotProduct(v3)))
                return null;
            if((t1>0&&t2>0&&t3>0)||(t1<0&&t2<0&&t3<0))
                return List.of(new GeoPoint(this,list.get(0)));
            return null;
        }
        return null;
    }

}