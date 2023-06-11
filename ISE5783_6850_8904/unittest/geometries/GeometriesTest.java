package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntersections() {
        Geometries geometries = new Geometries(
                new Triangle(new Point(0, 0, 5), new Point(0, 1, 5), new Point(1, 1, 5)),
                new Plane(new Point(0, 0, 1), new Point(0, 1, 1), new Point(1, 0, 1)),
                new Sphere(5, new Point(-2, 0, 0)));
        // ============ Equivalence Partitions Tests ==============
        // TC01 : some of the shape but not all of them are cut
        assertEquals(2, geometries.findIntersections(new Ray(new Point(0, -0.43, 0), new Vector(0.19, -0.05, 3.86))).size(), "Some shapes is cut geometries has been failed");
        // plane 1 point and sphere 1 point
        // =============== Boundary Values Tests ==================
        // TC02: An empty body collection
        Geometries t = new Geometries();
        assertNull(t.findIntersections(new Ray(new Point(0, 1, 2), new Vector(1, 2, 3))), "An empty body collection geometries has failed");
        //TC03 : No shape have Intsersections
        assertNull(geometries.findIntersections(new Ray(new Point(0, -6, 0), new Vector(0, 6, -8))), "No shape have Intsersections geometries has failed");
        // TC04 : Only one shape is cut (Sphere 2 points)
        assertEquals(2, geometries.findIntersections(new Ray(new Point(0, -6, 0), new Vector(0, 6, -2))).size(), "Only one shape is cut geometries has been failed");
        // TC05 : All shapes are cut (Sphere have 1 point)
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0.25, 0.5, -1), new Vector(0, 0, 6))).size(), "all shapes is cut geometries has been failed");


    }
}