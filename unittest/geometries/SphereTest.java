package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal()
    {
        /**
         * Test method for {@link .geometries.Sphere.getNormal(.geometries.Sphere)}.
         */
        Sphere s = new Sphere(3, new Point(5,6,3));
        // ============ 1 Equivalence Partition ==============
        assertDoesNotThrow(() -> s.getNormal(new Point(5,3,3)));
        Vector normal = s.getNormal(new Point(5,3,3));
        assertEquals(new Vector(0,-3,0),normal,"Get normal methode in Sphere at equivalence partition has been failed ");
    }


}