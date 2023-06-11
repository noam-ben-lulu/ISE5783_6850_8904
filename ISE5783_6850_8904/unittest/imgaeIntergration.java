import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.io.Console;
import java.io.InputStream;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;
//import static java.util.TreeMap.RED;

public class imgaeIntergration {
    private Scene         scene      = new Scene("my image");

    @Test
    public void Chapter7() {
        Camera camera = new Camera(new Point(-10000,5500,1000), new Vector(10,-5,-1), new Vector(1, -1, 15)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Sphere( 100,new Point(-0, 200, 0)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere( 100,new Point(-900, 200, 0)).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere( 100,new Point(-0, 1200, 0)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere( 100,new Point(-900, 1200, 0)).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Plane(new Point(0,0,-100),new Vector(0,0,1)).setEmission(new Color(92, 194, 225)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setkR(0.5)),
                new Polygon(new Point(0, 1400, 100),new Point(-900, 1400, 100),
                        new Point(-900, 0, 100),new Point(0, 0, 100)).setEmission(new Color(252,246,67))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(-900, 1400, 100),new Point(-900,1400,400),
                        new Point(0, 1400, 400),new Point(0, 1400, 100)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0).setShininess(60)),
                new Polygon(new Point(-900, 1400, 400),new Point(-900, 1000, 400),
                        new Point(0, 1000, 400),new Point(-0, 1400, 400)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, 1000, 600),new Point(-900, 1000, 400),
                        new Point(0, 1000, 400),new Point(-0, 1000, 600)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, 1000, 600),new Point(-900, 0, 600),
                        new Point(0, 0, 600),new Point(-0, 1000, 600)).setEmission(new Color(252,246,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, 0, 100),new Point(-900, 0, 600),
                        new Point(0, 0, 600),new Point(-0, 0, 100)).setEmission(new Color(252,246,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, 0, 100),new Point(-900, 0, 400),
                        new Point(-900, 1400, 400),new Point(-900, 1400, 100)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(40)),
                new Polygon(new Point(-900, 0, 600),new Point(-900, 0, 400),
                        new Point(-900, 700, 400),new Point(-900, 700, 600)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(60)),
                new Polygon(new Point(-900, 1000, 600),new Point(-900, 1000, 400),
                        new Point(-900, 700, 400),new Point(-900, 700, 600)).setEmission(new Color(67,239,252))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(20).setkT(0.5).setkR(0.25)),
                new Sphere(70,new Point(-700, 800, 500)).setEmission(new Color(172,91,10))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50)),
                new Polygon(new Point(-200, 1402, 200),new Point(-700, 1402, 200),
                        new Point(-700, 1402, 300),new Point(-200, 1402, 300)).setEmission(new Color(255,227,41))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)));

        scene.lights.add(new SpotLight(new Color(5000, 67, 67),new Point(-925,2000,1000),
                new Vector(9.25,-25,-10)).setkL(4E-5).setkQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("Car", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
