package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The `AmbientLight` class represents ambient lighting in a 3D scene.
 * It extends the `Light` class.
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructs an `AmbientLight` object with the given intensity and attenuation factors.
     *
     * @param Ia The intensity of the ambient light, represented as a `Color` object.
     * @param Ka The attenuation factors for the ambient light, represented as a `Double3` object.
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }
}
