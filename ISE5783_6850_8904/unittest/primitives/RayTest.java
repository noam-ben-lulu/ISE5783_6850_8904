package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class RayTest {

    @Test
    void findClosestPointTest() {
        Ray ray= new Ray((new Point(0,0,1)), new Vector(0,0,1));
        List<Point> list=null;
        assertNull(ray.findClosestPoint(list),"dont work");

        list =List.of(new Point(0, 0, 2),new Point(0,0,3),new Point(0,0,4));
        assertEquals(new Point(0,0,2),ray.findClosestPoint(list),"dont work");

        list =List.of(new Point(0, 0, 4),new Point(0,0,3),new Point(0,0,2));
        assertEquals(new Point(0,0,2),ray.findClosestPoint(list),"dont work");

    }
}