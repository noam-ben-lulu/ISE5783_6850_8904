package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * The Geometries class represents a collection of geometric shapes that are Intersectable.
 * It allows adding intersectable objects to the collection and finding intersections of a given ray with all the objects in the collection.
 */
public class Geometries extends Intersectable
{
    /**
     * Constructs a new Geometries object that contains the given list of intersectable objects.
     * @param list the list of intersectable objects to add to the collection.
     */
   private List<Intersectable> shapes;

    public Geometries()
    {
        shapes = new LinkedList<Intersectable>();
    }
    /**
     * Constructs a new Geometries object that contains the given intersectable objects.
     * @param geometries the intersectable objects to add to the collection.
     */
    public Geometries(Intersectable...geometries)
    {
        if(geometries!=null)
            shapes=List.of(geometries);
    }
    /**
     * Adds the given intersectable objects to the collection.
     * @param geometries the intersectable objects to add to the collection.
     */
    public void add(Intersectable...geometries)
    {
        if(geometries!=null)
            shapes.addAll(List.of(geometries));
    }
    /**
     * Finds all the intersection points of the given ray with the intersectable objects in the collection.
     * @param ray the ray to find intersection points with.
     * @return a list of all the intersection points of the given ray with the intersectable objects in the collection.
     */


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        boolean intersectExist = false;
        for (Intersectable element : this.shapes) {
            if (element.findIntersections(ray) != null) {
                intersectExist = true;
                break;
            }
        }
        if (intersectExist == false) {
            return null;
        }

        ArrayList<GeoPoint> IntersectionsPoints = new ArrayList<GeoPoint>();
        for (Intersectable element : this.shapes) {
            if (element.findIntersections(ray) != null) {
                IntersectionsPoints.addAll(element.findGeoIntersections(ray));
            }
        }
        return List.of(IntersectionsPoints.toArray(new GeoPoint[0]));
    }
}

