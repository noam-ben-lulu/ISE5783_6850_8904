package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

public abstract class Geometry extends Intersectable {
   protected Color emission = Color.BLACK;
    private Material material=new Material();

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * A method that returns the normal vector to the geometry at a specific point
     * @param point A point on the geometry
     * @return The normal vector to the geometry at the given point
     */
    public abstract  Vector getNormal(Point point);

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}