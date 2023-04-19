package primitives;

import static java.lang.Math.sqrt;

public class Vector extends Point {
    /** Constructor for creating a new Vector object with given x, y, and z coordinates. **/
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("you put Zero vector");
    }

    Vector(Double3 XYZ) {
        super(XYZ);
        if (XYZ.equals(Double3.ZERO))
            throw new IllegalArgumentException("you put Zero vector");
    }

    /**
     * Returns a new Vector object that represents the sum of this Vector and the given Vector.
     *
     * @param vec The Vector to add to this Vector.
     * @return A new Vector object that represents the sum of this Vector and the given Vector.
     */
    @Override
    public Vector add(Vector vec) {
        return new Vector(this.xyz.add(vec.xyz));
    }

    /**
     * Returns a new Vector object that represents this Vector scaled by the given scalar value.
     *
     * @param scal The scalar value to scale this Vector by.
     * @return A new Vector object that represents this Vector scaled by the given scalar value.
     */
    public Vector scale(double scal) {
        return new Vector(this.xyz.scale(scal));
    }

    /**
     * Returns the dot product of this Vector and the given Vector.
     *
     * @param vec The Vector to calculate the dot product with.
     * @return The dot product of this Vector and the given Vector.
     */
    public double dotProduct(Vector vec) {
        return (vec.xyz.d3 * this.xyz.d3 + vec.xyz.d2 * this.xyz.d2 + vec.xyz.d1 * this.xyz.d1);
    }

    /**
     * Returns a new Vector object that represents the cross product of this Vector and the given Vector.
     *
     * @param vec The Vector to calculate the cross product with.
     * @return A new Vector object that represents the cross product of this Vector and the given Vector.
     */
    public Vector crossProduct(Vector vec) {
        return (new Vector((this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2),
                -(this.xyz.d1 * vec.xyz.d3) + (this.xyz.d3 * vec.xyz.d1),
                (this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1)));
    }

    /**
     * Returns the squared length of this Vector.
     *
     * @return The squared length of this Vector.
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Returns the length of this Vector.
     *
     * @return The length of this Vector.
     */
    public double length() {
        return (sqrt(lengthSquared()));
    }

    /**
     * Returns a new Vector object that represents this Vector normalized.
     *
     * @return A new Vector object that represents this Vector normalized.
     */
    public Vector normalize() {
        double len = length();
        return new Vector(
                xyz.d1 /len,
                xyz.d2 /len,
                xyz.d3 /len
        );
    }


    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz.toString() +
                '}';
    }

}