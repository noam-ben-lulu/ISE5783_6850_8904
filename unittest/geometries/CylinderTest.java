package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CylinderTest {

    @Test
    void testgetNormal() {
        Tube t= new Cylinder(2,new Ray(new Point(0,0,0),new Vector(1,0,0)),2);
        // ============  Equivalence Partitions Tests 1 ==============
        assertDoesNotThrow(() -> t.getNormal(new Point(1,1,0)));
        assertEquals(1,t.getNormal(new Point(1,1,0)).length(),0.0000001);
        assertEquals(new Vector(0,1,0),t.getNormal(new Point(1,1,0)));
        // ============  Equivalence Partitions Tests 2 ==============
        assertDoesNotThrow(() -> t.getNormal(new Point(0,1,0)));
        assertEquals(1,t.getNormal(new Point(0,1,0)).length(),0.0000001);
        assertEquals(new Vector(-1,0,0),t.getNormal(new Point(0,1,0)));
        // ============  Equivalence Partitions Tests 3 ==============
        assertDoesNotThrow(() -> t.getNormal(new Point(2,1,0)));
        assertEquals(1,t.getNormal(new Point(2,1,0)).length(),0.0000001);
        assertEquals(new Vector(1,0,0),t.getNormal(new Point(2,1,0)));
        // =============== Boundary Values Tests 1==================
        assertDoesNotThrow(() -> t.getNormal(new Point(0,0,0)));
        assertEquals(1,t.getNormal(new Point(0,0,0)).length(),0.0000001);
        assertEquals(new Vector(-1,0,0),t.getNormal(new Point(0,0,0)));
        // =============== Boundary Values Tests 2==================
        assertDoesNotThrow(() -> t.getNormal(new Point(2,0,0)));
        assertEquals(1,t.getNormal(new Point(2,0,0)).length(),0.0000001);
        assertEquals(new Vector(1,0,0),t.getNormal(new Point(2,0,0)));


    }
}