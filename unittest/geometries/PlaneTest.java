package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTest {
    @Test
    public void testConstructor()
    {
        // ============ Equivalence Partition Test ==============
        Plane plane= new Plane(new Point(0,0,0),new Point(1,0,0),new Point(0,0,1));
        Vector normal = plane.getNormal();
        assertTrue(isZero(1-normal.length()), "Plane- constructor- the normal is not a unit vector");
        assertTrue(isZero(new Vector(1,0,0).dotProduct(normal)) && isZero(new Vector(0,0,1).dotProduct(normal)),"Plane - constructor - the normal is wrong");
        // ===============  Boundary Values Tests 1 ==================
        assertThrows(IllegalArgumentException.class,()->new Plane(new Point(1,0,0),new Point(1,0,0),new Point(6,5,3)),"Plane- constructor - Does not throw an exception in case of two identical points");
        // ===============  Boundary Values Tests 2 ==================
        assertThrows(IllegalArgumentException.class,()->new Plane(new Point(0,1,0),new Point(0,2,0),new Point(0,3,0)),"Plane- constructor - Does not throw an error when the points are on the same line");

    }
    @Test
    /**
     *Test method for {@link .geometries.Plane.GetNormal(.geometries.Plane)}.
     */
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
        Plane pol = new Plane(pts[0] ,pts[1], pts[2]);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)));
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "plane normal is not orthogonal to one of the edges");
    }
}