import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testsubtract()
    {
        /**
         * Test method for {@link primitives.Point#subtract(primitives.Point)}.
         */
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the subtruct methode is working
         Point p1 = new Point(5,3,2);
         Point p2 = new Point(1,2,1);
        assertDoesNotThrow(() -> p1.subtract(p2));
        Vector result = p1.subtract(p2);
        Vector Expected = new Vector(4,1,1);
        assertEquals(Expected,result,"ERROR: Point - Point does not work correctly");
        // =============== Boundary Values Tests ==================
        //TC02: return a zero vector
        assertThrows(IllegalArgumentException.class ,() -> p1.subtract( new Point (5,3,2)));
    }

    @Test
    void testadd()
    {
        /**
         * Test method for {@link primitives.Point#add(primitives.Point)}.
         */
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the add methode is working
        Point p1 = new Point (1,2,3);
        Vector v1 = new Vector (-1,-2,-3);
        assertDoesNotThrow(() -> p1.add(v1));
        Point result = p1.add(v1);
        Point Expected = new Point(0,0,0);
        assertEquals(Expected,result,"ERROR: Point + Vector does not work correctly");

    }

    @Test
    void testdistanceSquared()
    {
        /**
         * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
         */
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point (4,3,0);
        Point p2 = new Point (6,4,3);
        assertDoesNotThrow(() -> p1.distanceSquared(p2));
        double result= p1.distanceSquared(p2);
        double  Expected = 14;
        assertEquals(Expected,result, 0.00001,"ERROR:distanceSquared does not work correctly");
    }

    @Test
    void testdistance()
    {
        /**
         * Test method for {@link primitives.Point#distance(primitives.Point)}.
         */
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point (4,3,0);
        Point p2 = new Point (6,4,3);
        assertDoesNotThrow(() -> p1.distance(p2));
        double result= p1.distance(p2);
        double  Expected = sqrt(14);
        assertEquals(Expected,result, 0.00001,"ERROR:distance does not work correctly");
    }
}