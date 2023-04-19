package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */

    @Test
    void testadd() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        assertDoesNotThrow(() ->v1.add(v2));
        assertEquals(new Vector(-1,-2,-3),v1.add(v2) , "ERROR: Vector + itself throws wrong exception");
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->v1.add(new Vector(-1,-2,-3)));
    }
    /**
     * Test method for {@link primitives.Vector#scale(primitives.Vector)}.
     */
    @Test
    void testscale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertDoesNotThrow(() ->v1.scale(2));
        assertEquals(new Vector(2,4,6),v1.scale(2) , "ERROR: Vector + itself throws wrong exception");
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->v1.scale(0));


    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testdotProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        assertDoesNotThrow(() ->v1.dotProduct(v2));
        assertEquals(-28,v1.dotProduct(v2) ,0.00001,"ERROR: dotProduct() wrong value");
        // =============== Boundary Values Tests ==================
        assertTrue(isZero(v1.dotProduct((new Vector(-2,1,0)))), "dotProduct() Does not work for a perpendicular vector");


    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(vr.length(),v1.length() * v2.length() , 0.00001, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()(primitives.Vector)}.
     */
    @Test
    void testlengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertDoesNotThrow(() ->v1.lengthSquared());
        assertEquals(14,v1.lengthSquared(),0.00001,"ERROR: lengthSquared() wrong value");
    }
    /**
     * Test method for {@link Vector#length()(primitives.Vector)}.
     */
    @Test
    void testlength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(0, 4, 3);
        assertDoesNotThrow(() ->v1.length());
        assertEquals(5,v1.length(),0.00001,"ERROR: length() wrong value");
    }
    /**
     * Test method for {@link Vector#normalize()(primitives.Vector)}.
     */
    @Test
    void testnormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertDoesNotThrow(() -> v1.normalize());
        assertTrue(isZero(v1.normalize().length()-1),"ERROR: the normalized vector is not a unit vector"  );


    }
}