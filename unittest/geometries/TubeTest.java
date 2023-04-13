package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    /**
     *Test method for {@link .geometries.Tube.GetNormal(.geometries.Tube)}.
     */
    void getNormal()
    {
        Tube t= new Tube(1,new Ray(new Point(0,0,0),new Vector(0,1,0)));
        // ============ 1 Equivalence Partitions Tests ==============
        assertDoesNotThrow(() -> t.getNormal(new Point(1,1,0)));
        assertEquals(1,t.getNormal(new Point(1,1,0)).length(),0.0000001); // check if he normalized
        assertEquals(new Vector(1,0,0),t.getNormal(new Point(1,1,0)));
        // =============== Boundary Values Tests ==================
        assertDoesNotThrow(() -> t.getNormal(new Point(1,0,0)));
        assertEquals(1,t.getNormal(new Point(1,0,0)).length(),0.0000001);//BVA: 1 extreme case when 𝑷 − 𝑷𝟎 is orthogonal to 𝒗 in that case the normal is simply the p-p0 vector
        assertEquals(new Vector(1,0,0),t.getNormal(new Point(1,0,0)),"Tube- GetNormal- boundary value test has been failed ");
    }
}