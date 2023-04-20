package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable
{
   protected List<Intersectable> list;

    public Geometries()
    {
        list = new LinkedList<>();
    }
    public Geometries(Intersectable...geometries)
    {


    }
    public void add(Intersectable...geometries)
    {

    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
