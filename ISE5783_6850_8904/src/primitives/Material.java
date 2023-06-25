package primitives;

/**
 * The `Material` class represents the material properties of an object in a 3D scene.
 */
public class Material {
    public Double3 kD = Double3.ZERO;  // Diffuse reflection coefficient
    public Double3 kS = Double3.ZERO;  // Specular reflection coefficient
    public int nShininess = 0;  // Shininess coefficient
    public Double3 kT=Double3.ZERO, kR=Double3.ZERO;

    public double kB=0, kG=0;

    public double getkB() {
        return kB;
    }
    public double getkG() {
        return kG;
    }



    public Material setkB(double kB) {
        this.kB = kB;
        return this;

    }

    public Material setkG(double kG) {
        this.kG = kG;
        return this;
    }

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

    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;

    }
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
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setkT(double kT) {
        this.kT=new Double3(kT);
        return this;
    }
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Double3 getkR() {
        return kR;
    }

    public Double3 getkT() {
        return kT;
    }
}