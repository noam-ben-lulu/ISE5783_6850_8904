package primitives;

public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructs a new Ray object with the given origin point and direction vector.
     *
     * @param point  The origin point of the Ray.
     * @param vector The direction vector of the Ray.
     */
    public Ray(Point point, Vector vector) {
        p0 = point;
        dir = vector.normalize();
    }

    /**
     * Returns the origin point of the Ray.
     *
     * @return The origin point of the Ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the Ray.
     *
     * @return The direction vector of the Ray.
     */
    public Vector getDir() {
        return dir;
    }
    public  Point getPoint(double t) {return p0.add(dir.scale(t));}

    /**
     * Returns a string representation of the Ray object, in the format "Ray{p0=<p0>, dir=<dir>}".
     *
     * @return A string representation of the Ray object.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}