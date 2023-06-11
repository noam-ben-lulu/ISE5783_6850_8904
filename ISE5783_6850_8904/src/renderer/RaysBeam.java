package renderer;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

/**
 * The RaysBeam class provides a method for generating a list of rays for super-sampling.
 */
public class RaysBeam {
    private static final Vector z = new Vector(0, 0, 1);
    private static final Vector y = new Vector(0, 1, 0);

    /**
     * Generates a list of rays for super-sampling.
     *
     * @param startPoint   The starting point for the rays
     * @param original     The original point
     * @param l            The vector direction
     * @param numberOfRays The number of rays to generate
     * @param radius       The radius of the super-sampling area
     * @return The list of rays for super-sampling
     */
    public static List<Ray> superSampling(Point startPoint, Point original, Vector l, double numberOfRays, double radius) {
        List<Ray> rayBeam = new ArrayList<>();

        try {
            Vector l1;
            try {
                l1 = l.crossProduct(l.add(z));
            } catch (Exception ex) {
                l1 = l.crossProduct(l.add(y));
            }
            Vector l2 = l.crossProduct(l1);

            for (int i = 0; i < Math.sqrt(numberOfRays) + 1; i++) {
                for (int t = 0; t < Math.sqrt(numberOfRays) + 1; t++) {
                    Point newPoint = startPoint.add(l1.scale(radius * (1 - ((i * 2) / numberOfRays))));
                    newPoint = newPoint.add(l2.scale(radius * (1 - ((t * 2) / numberOfRays))));
                    rayBeam.add(new Ray(original, newPoint.subtract(original)));
                }
            }

            Point help = startPoint;
            Ray helpRay = new Ray(original, help.subtract(original));
            if (!rayBeam.contains(helpRay)) {
                rayBeam.add(helpRay);
            }
        } catch (Exception e) {
            // Handle exception if necessary
        }

        return rayBeam;
    }
}
