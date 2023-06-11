package lighting;

import primitives.Color;

/**
 * The `Light` class represents a light source in a 3D scene.
 * It is an abstract class.
 */
abstract class Light {

    private Color intensity;

    /**
     * Constructs a `Light` object with the given intensity.
     *
     * @param intensity The intensity of the light, represented as a `Color` object.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return The intensity of the light, represented as a `Color` object.
     */
    public Color getIntensity() {
        return intensity;
    }
}
