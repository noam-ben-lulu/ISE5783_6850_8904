import geometries.*;
import org.junit.jupiter.api.Test;
import renderer.Camera;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class IntegrationTest {
    public double countIntersection(Geometry geometry, Camera camera, int Nx, int Ny) {
        double sum_intersection = 0;
        Ray ray;

        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                ray = camera.constructRay(Nx,Ny , i, j);
                if (geometry.findIntersections(ray) != null) {
                    ++sum_intersection;
                    if (geometry.findIntersections(ray).size() == 2) {
                        ++sum_intersection;
                    }
                }
            }
        }
        return sum_intersection;
    }
    @Test
    void integrationWithSphere() {
        //2 intersection points
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(3, 3).setVPDistance(1);
        Sphere sphere = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2, countIntersection(sphere, camera,3,3), "Wrong number of intersection");
        //18 intersection points
        camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3).setVPDistance(1);
        sphere = new Sphere(2.5, new Point(0,0,-2.5));
        assertEquals(18, countIntersection(sphere, camera,3,3), "Wrong number of intersection");
        //10 intersection points
        camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3).setVPDistance(1);
        sphere = new Sphere(2, new Point(0,0,-2));
        assertEquals(10, countIntersection(sphere, camera,3,3), "Wrong number of intersection");
        //9 intersection points
        camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3).setVPDistance(1);
        sphere = new Sphere(4, new Point(0,0,-2));
        assertEquals(9, countIntersection(sphere, camera,3,3), "Wrong number of intersection");
        //0 intersection points
        camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3).setVPDistance(1);
        sphere = new Sphere(0.5, new Point(0,0,1));
        assertEquals(0, countIntersection(sphere, camera,3,3), "Wrong number of intersection");
    }


    @Test
    void integrationWithPlane()
    {
        //9 intersection points
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3).setVPDistance(1);
        Plane plane = new Plane(new Point(0,0,-2), new Vector(0, 0,1));
        assertEquals(9, countIntersection(plane, camera,3,3), "Wrong number of intersection");
        //9 intersection points
        plane = new Plane(new Point(0,0,-2), new Vector(0, -0.5,1));
        assertEquals(9, countIntersection(plane, camera,3,3), "Wrong number of intersection");
        //6 intersection points
        plane = new Plane(new Point(0,0,-20), new Vector(0, -1,1));
        assertEquals(6, countIntersection(plane, camera,3,3), "Wrong number of intersection");

    }

    @Test
    void integrationWithTriangle()
    {
        //1 intersection points
        Camera camera = new Camera(new Point(0,0,0), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3).setVPDistance(1);
        Triangle triangle = new Triangle(new Point(0,1,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(1, countIntersection(triangle, camera,3,3), "Wrong number of intersection");
        //2 intersection points
        triangle = new Triangle(new Point(0,20,-2), new Point(1,-1,-2), new Point(-1,-1,-2));
        assertEquals(2, countIntersection(triangle, camera,3,3), "Wrong number of intersection");
    }
}