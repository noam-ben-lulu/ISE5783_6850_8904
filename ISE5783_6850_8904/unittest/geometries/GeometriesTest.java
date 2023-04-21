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
        Geometries test = new Geometries();
        // =============== Boundary Values Tests ==================
        // TC01: An empty body collection
        assertEquals(0,test.shapes.size(),"An empty body collection test has failed");
        test.add(new Triangle(new Point(0,0,5),new Point(0,1,5),new Point(1,1,5)));
        test.add(new Plane(new Point(0,0,1),new Point(0,1,1),new Point(1,0,1)));
        test.add(new Sphere(5,new Point(-2,0,0)));
        //TC02 : No shape have Intsersections
        assertNull(test.findIntsersections(new Ray(new Point(0,-6,0),new Vector(0,6,-2))),"No shape have Intsersections test has failed");
        // TC03 : Only one shape is cut
        assertEquals(1,test.findIntsersections(new Ray(new Point(0,-4,0),new Vector(0,4,1.42))).size(),"Only one shape is cut test has been failed");
        // TC04 : All shapes are cut
        assertEquals(4,test.findIntsersections(new Ray(new Point(0.25,0.5,-1),new Vector(0,0,6))).size(),"all shapes is cut test has been failed");
        // ============ Equivalence Partitions Tests ==============
        // TC05 : some of the shape but not all of them are cut
        assertEquals(3,test.findIntsersections(new Ray(new Point(0,-0.43,0),new Vector(0.19,-0.05,3.86))).size(),"Some shapes is cut test has been failed");
        // plane 1 point and sphere 2 point




    }
}