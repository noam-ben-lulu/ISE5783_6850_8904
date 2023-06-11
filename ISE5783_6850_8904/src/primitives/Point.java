package primitives;

import java.util.Objects;

import static java.lang.Math.sqrt;

public class Point {
    Double3 xyz;
    public static final Point ZERO = new Point(0,0,0) ;
    /**
     * Constructor for creating a new Point object with given x, y, and z coordinates.
     *
     * @param x The x coordinate of the Point
     * @param y The y coordinate of the Point
     * @param z The z coordinate of the Point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor for creating a new Point object with a given Double3 object.
     *
     * @param XYZ The Double3 object containing the x, y, and z coordinates of the Point
     */
    Point(Double3 XYZ) {
        this.xyz = XYZ;
    }

    /**
     * Calculates the vector from a given vertex to the current Point.
     *
     * @param vertex The vertex from which to calculate the vector
     * @return The vector from the given vertex to the current Point
     */
    public Vector subtract(Point vertex) {
        return new Vector(this.xyz.subtract(vertex.xyz));
    }

    /**
     * Adds a given vector to the current Point and returns a new Point object.
     *
     * @param vector The vector to add to the current Point
     * @return A new Point object representing the result of the addition
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * Calculates the squared distance between the current Point and a given Point.
     *
     * @param point The Point to calculate the distance to
     * @return The squared distance between the current Point and the given Point
     */
    public double distanceSquared(Point point) {
        return (xyz.d1 - (point.xyz.d1)) * (xyz.d1 - (point.xyz.d1)) +
                (xyz.d2 - (point.xyz.d2)) * (xyz.d2 - (point.xyz.d2)) +
                (xyz.d3 - (point.xyz.d3)) * (xyz.d3 - (point.xyz.d3));
    }

    /**
     * Calculates the distance between the current Point and a given Point.
     *
     * @param point The Point to calculate the distance to
     * @return The distance between the current Point and the given Point
     */
    public double distance(Point point) {
        return sqrt(distanceSquared(point));
    }


    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz.toString() +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return Objects.equals(xyz, point.xyz);
    }

    public double getX() {
        return xyz.d1;
    }
}