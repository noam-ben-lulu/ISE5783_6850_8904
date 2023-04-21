package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable
{
   protected List<Intersectable> shapes;

    public Geometries()
    {
        shapes = new LinkedList<>();
    }
    public Geometries(Intersectable...geometries)
    {
        shapes=List.of(geometries);
    }
    public void add(Intersectable...geometries)
    {
        shapes.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
       // List intsersectPoint=null;
       // intsersectPoint.addAll(lst.forEach(lst->findIntsersections(ray)));
       // return intsersectPoint;
        return null;
    }
}
