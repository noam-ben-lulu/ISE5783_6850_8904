package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntsersections()
    {
        Geometries test = null;
        // =============== Boundary Values Tests ==================
        // TC01: An empty body collection
        assertNull(test.findIntsersections(new Ray(new Point(1,2,3),new Vector(4,5,6))),"An empty body collection test has failed");
        //TC02 : No shape have Intsersections
        test.add(new Triangle(new Point(0,0,2),new Point(0,5,0),new Point(0,1,0)));
        test.add(new Plane(new Point(0,0,2),new Point(0,5,0),new Point(3,0,0)));
        test.add(new Sphere(5,new Point(-2,0,0)));
        assertNull(test.findIntsersections(new Ray(new Point(6,0,0),new Vector(-5,7,0))),"No shape have Intsersections test has failed");
        // TC03 : Only one shape is cut
        assertEquals(1,test.list.get(1).findIntsersections(new Ray(new Point(0,0,-1),new Vector(1,1,2))).size(),"Only one shape is cut test has been failed");
        // TC04 : All shapes are cut
        int sum=0;




    }
}