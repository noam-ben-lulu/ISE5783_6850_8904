package primitives;

/**
 * The `Material` class represents the material properties of an object in a 3D scene.
 */
public class Material {
    public Double3 kD = Double3.ZERO;  // Diffuse reflection coefficient
    public Double3 kS = Double3.ZERO;  // Specular reflection coefficient
    public int nShininess = 0;  // Shininess coefficient

    public Double3 kT = Double3.ZERO;  // Transparency coefficient
    public Double3 kR = Double3.ZERO;  // Reflection coefficient

    /**
     * Sets the diffuse reflection coefficient (kD) of the material.
     *
     * @param kD The diffuse reflection coefficient as a `Double3` object.
     * @return The updated `Material` object.
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the specular reflection coefficient (kS) of the material.
     *
     * @param kS The specular reflection coefficient as a `Double3` object.
     * @return The updated `Material` object.
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (kD) of the material.
     *
     * @param kD The diffuse reflection coefficient as a double value.
     * @return The updated `Material` object.
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular reflection coefficient (kS) of the material.
     *
     * @param kS The specular reflection coefficient as a double value.
     * @return The updated `Material` object.
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the reflection coefficient (kR) of the material.
     *
     * @param kR The reflection coefficient as a `Double3` object.
     * @return The updated `Material` object.
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the reflection coefficient (kR) of the material.
     *
     * @param kR The reflection coefficient as a double value.
     * @return The updated `Material` object.
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Sets the shininess coefficient (nShininess) of the material.
     *
     * @param nShininess The shininess coefficient.
     * @return The updated `Material` object.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (kD) of the material.
     *
     * @param kD The diffuse reflection coefficient as a `Double3` object.
     * @return The updated `Material` object.
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (kD) of the material.
     *
     * @param kD The diffuse reflection coefficient as a double value.
     * @return The updated `Material` object.
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the transparency coefficient (kT) of the material.
     *
     * @param kT The transparency coefficient as a double value.
     * @return The updated `Material` object.
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Sets the transparency coefficient (kT) of the material.
     *
     * @param kT The transparency coefficient as a `Double3` object.
     * @return The updated `Material` object.
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Returns the reflection coefficient (kR) of the material.
     *
     * @return The reflection coefficient as a `Double3` object.
     */
    public Double3 getkR() {
        return kR;
    }

    /**
     * Returns the transparency coefficient (kT) of the material.
     *
     * @return The transparency coefficient as a `Double3` object.
     */
    public Double3 getkT() {
        return kT;
    }
}
