import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
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

import static java.awt.Color.WHITE;
//import static java.util.TreeMap.RED;

public class FinalImage {
    private Scene         scene      = new Scene("my image");

    @Test
    public void Cars() {
        Camera camera = new Camera(new Point(-4000,4000,1000), new Vector(4,-4,-1),
                new Vector(1, -1, 8)) //
                .setVPSize(1000, 1000).setVPDistance(1000).setThreads(4);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).setBackground(new Color(0,213,355));

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
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(60)),
                new Polygon(new Point(-900, 1000, 600),new Point(-900, 1000, 400),
                        new Point(-900, 700, 400),new Point(-900, 700, 600)).setEmission(new Color(67,239,252))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(20).setkT(0.5).setkR(0.25)),
                new Sphere(70,new Point(-700, 800, 500)).setEmission(new Color(172,91,10))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50)),
                new Polygon(new Point(-200, 1402, 200),new Point(-700, 1402, 200),
                        new Point(-700, 1402, 300),new Point(-200, 1402, 300)).setEmission(new Color(255,227,41))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),


                //////////////////////////////////////////////////////////////////////
                new Sphere( 100,new Point(-0, -3300, 0)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere( 100,new Point(-900, -3300, 0)).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere( 100,new Point(-0, -2300, 0)).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setkT(new Double3(0.5, 0, 0))),
                new Sphere( 100,new Point(-900, -2300, 0)).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(0, -2100, 100),new Point(-900, -2100, 100),
                        new Point(-900, -3500, 100),new Point(0, -3500, 100)).setEmission(new Color(252,246,67))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(-900, -2100, 100),new Point(-900,-2100,400),
                        new Point(0, -2100, 400),new Point(0, -2100, 100)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0).setShininess(60)),
                new Polygon(new Point(-900, -2100, 400),new Point(-900, -2500, 400),
                        new Point(0, -2500, 400),new Point(-0, -2100, 400)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, -2500, 600),new Point(-900, -2500, 400),
                        new Point(0, -2500, 400),new Point(-0, -2500, 600)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, -2500, 600),new Point(-900, -3500, 600),
                        new Point(0, -3500, 600),new Point(-0, -2500, 600)).setEmission(new Color(252,246,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, -3500, 100),new Point(-900, -3500, 600),
                        new Point(0, -3500, 600),new Point(-0, -3500, 100)).setEmission(new Color(252,246,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(20)),
                new Polygon(new Point(-900, -3500, 100),new Point(-900, -3500, 400),
                        new Point(-900, -2100, 400),new Point(-900, -2100, 100)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(40)),
                new Polygon(new Point(-900, -3500, 600),new Point(-900, -3500, 400),
                        new Point(-900, -2800, 400),new Point(-900, -2800, 600)).setEmission(new Color(0,10,67))
                        .setMaterial(new Material().setKd(1).setKs(0.1).setShininess(60)),
                new Polygon(new Point(-900, -2500, 600),new Point(-900, -2500, 400),
                        new Point(-900, -2800, 400),new Point(-900, -2800, 600)).setEmission(new Color(67,239,252))
                        .setMaterial(new Material().setKd(0.5).setKs(0.1).setShininess(20).setkT(0.5).setkR(0.25)),
                new Sphere(70,new Point(-700, -2700, 500)).setEmission(new Color(172,91,10))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50)),
                new Polygon(new Point(-200, -2098, 200),new Point(-700, -2098, 200),
                        new Point(-700, -2098, 300),new Point(-200, -2098, 300)).setEmission(new Color(255,227,41))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
/////////////////////////////////////////////////////////
                new Plane(new Point(0,0,-100),new Vector(0,0,1)).setEmission(new Color(119, 250, 4)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setkR(0.1)),
                new Polygon(new Point(200, -10000, -99),new Point(-1300, -10000, -99),
                        new Point(-1300, 10000,-99 ),new Point(200, 10000, -99)).setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, -800, -98),new Point(-650, -800, -98),
                        new Point(-650, 0,-98 ),new Point(-550, 0, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, 1200, -98),new Point(-650, 1200, -98),
                        new Point(-650, 400,-98 ),new Point(-550, 400, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, 1600, -98),new Point(-650, 1600, -98),
                        new Point(-650, 2400,-98 ),new Point(-550, 2400, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, 2800, -98),new Point(-650, 2800, -98),
                        new Point(-650, 3600,-98 ),new Point(-550, 3600, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, -1200, -98),new Point(-650, -1200, -98),
                        new Point(-650, -2000,-98 ),new Point(-550, -2000, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, -2400, -98),new Point(-650, -2400, -98),
                        new Point(-650, -3200,-98 ),new Point(-550, -3200, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, -3600, -98),new Point(-650, -3600, -98),
                        new Point(-650, -4400,-98 ),new Point(-550, -4400, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, -4800, -98),new Point(-650, -4800, -98),
                        new Point(-650, -5600,-98 ),new Point(-550, -5600, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
                new Polygon(new Point(-550, -6000, -98),new Point(-650, -6000, -98),
                        new Point(-650, -6800,-98 ),new Point(-550, -6800, -98)).
                        setEmission(new Color(255, 255, 255)) //
                        .setMaterial(new Material().setKd(0).setKs(1).setShininess(20).setkR(0.1)),
///////////////////////////////////////////////////////////////////////////////////////////////////////////
                new Polygon(new Point(-2200,-1200,-100),new Point(-2200,-1200,1500),
                        new Point(-1400,200,1500), new Point(-1400,200,-100)).
                        setEmission(new Color(0, 0, 0)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(20).setkT(1).setkB(5)));
        scene.lights.add(new SpotLight(new Color(1000, 25, 25),new Point(-925,2000,1000),
                new Vector(9.25,-25,-10)).setkL(4E-5).setkQ(2E-7));
        scene.lights.add(new DirectionalLight(new Color(4, 160, 0), new Vector(-9.25,25,10)));
        scene.lights.add( new PointLight(new Color(255, 20, 20), new Point(0, 0, 1000)) //
                .setkL(0.000004).setkQ(0.000000006));
        scene.lights.add(new SpotLight(new Color(100, 255, 25),new Point(-0,2000,1000),
                new Vector(15,-20,-10)).setkL(4E-5).setkQ(2E-7));
        scene.lights.add( new PointLight(new Color(255, 100, 20), new Point(-1000, 500, 300)) //
                .setkL(4E-5).setkQ(2E-7));
        scene.lights.add( new PointLight(new Color(255, 100, 20), new Point(-1000, -3000, 300)) //
                .setkL(4E-5).setkQ(2E-7));

        ImageWriter imageWriter = new ImageWriter("Cars", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene).set_Glossy_promoted(17*17,20,50).setUseb(true)) //
                .renderImage() //
                .writeToImage();
    }

}
