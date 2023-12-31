/**
 * The RayTracerBasic class is a basic implementation of a ray tracer.
 * It extends the abstract class RayTracerBase.
 * It is responsible for tracing a given ray and returning the color of the closest intersection between the ray and
 * the scene's geometries.
 */
package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.StrictMath.pow;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static  double DISTANCE = 20;
    private static  double NUM_OF_RAYS = 17*17;
    private double sizeGrid=17;



    /**
     * Constructs a new RayTracerBasic object with the given scene.
     *
     * @param scene the scene to be traced
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Traces the given ray and returns the color of the closest intersection between the ray and the scene's geometries.
     *
     * @param ray the ray to be traced
     * @return the color of the closest intersection between the ray and the scene's geometries
     */
    @Override
    public Color traceRay(Ray ray) {
        // Finds the intersections of the given ray with the scene's geometries
        GeoPoint point = findClosestIntersection(ray);
        // If no intersections are found, returns the background color of the scene
        if (point == null)
            return scene.background;
        // Calculates and returns the color of the point of intersection
        return calcColor(point,ray);
    }
    /**
     * Constructs a reflected ray based on the point, incident vector, and surface normal vector.
     *
     * @param p The point of reflection
     * @param v The incident vector
     * @param n The surface normal vector
     * @return The reflected ray
     */
    public Ray constructReflectedRay(Point p,Vector v, Vector n){
        if (v.dotProduct(n)==0)
            return new Ray(p,v);
        Ray Reflected= new Ray(p,(v.subtract(n.scale(2*(v.dotProduct(n))))),n);
        return Reflected;
    };
    /**
     * Constructs a refracted ray based on the point, incident vector, and surface normal vector.
     *
     * @param p The point of refraction
     * @param v The incident vector
     * @param n The surface normal vector
     * @return The refracted ray
     */
    public Ray constructRefractedRay(Point p, Vector v,Vector n){
        return new Ray(p,v,n);
    };

    /**
     * Calculates the color with global effects (reflection and refraction) for a given intersection point.
     *
     * @param gp    The intersection point
     * @param ray   The ray being traced
     * @param level The current recursion level
     * @param k     The attenuation factor
     * @return The color with global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Ray reflectedRay = constructReflectedRay(gp.point, v, n);
        Double3 kr = material.getkR(), kkr = k.product(kr);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
        {
            if (!Useg) {
                GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
                if(reflectedPoint != null)
                    color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr)
                            .scale(kr));
                else
                    color=color.add(scene.background).scale(kr);

            }
            else {
                List<Ray> rays = GlossyGrid( new Ray(gp.point,reflectedRay.getDir(),n),-1,material.kG,n);
                color = color.add(average_color_calculator(rays).scale(kr));

            }
        }
        Ray refractedRay = constructRefractedRay(gp.point, v,n);
        Double3 kt = material.getkT(), kkt = k.product(kt);
        if (!kkt.lowerThan( MIN_CALC_COLOR_K)) {
            if (!Useb) {
                GeoPoint refractedPoint = findClosestIntersection(refractedRay);
                if (refractedPoint != null) color =
                        color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
                else
                    color=color.add(scene.background).scale(kt);

            }
            else
            {
                List<Ray> rays = GlossyGrid(new Ray(gp.point, v, n),1,material.getkB(), n);
                color = color.add(average_color_calculator(rays).scale(kt));
            }
        }

        return color;
    }
    /**
     * Calculates the average color by tracing rays and finding the closest intersections.
     *
     * @param rays The list of rays to trace.
     * @return The average color calculated from the intersected points.
     */
    public Color average_color_calculator(List<Ray> rays) {
        Color aver = Color.BLACK;
        if (rays.size() == 0)
            return aver;

        for (Ray ray : rays) {
            GeoPoint point = findClosestIntersection(ray);

            // If no intersections are found, add the background color of the scene
            if (point == null)
                aver = aver.add(scene.background);
            else {
                Color c = calcColor(point, ray);
                aver = aver.add(c);
            }
        }

        return aver.reduce(new Double3(rays.size()));
    }

    /**
     * Generates a glossy grid of rays based on the provided parameters.
     *
     * @param ray        The original ray.
     * @param direction  The direction of the grid (1 for refraction, -1 for reflection).
     * @param glossy     The glossiness value.
     * @param n          The normal vector.
     * @return The list of rays in the glossy grid.
     */
    List<Ray> GlossyGrid(Ray ray, int direction, double glossy, Vector n) {
        int numOfRowCol = isZero(glossy) ? 1 : (int) Math.ceil(Math.sqrt(NUM_OF_RAYS));
        if (numOfRowCol == 1) {
            return List.of(ray);
        }

        Vector Vup;
        double Ax = Math.abs(ray.getDir().getX());
        double Ay = Math.abs(ray.getDir().getY());
        double Az = Math.abs(ray.getDir().getZ());

        if (Ax < Ay) {
            Vup = Ax < Az ? new Vector(0, -ray.getDir().getZ(), ray.getDir().getY()) :
                    new Vector(-ray.getDir().getY(), ray.getDir().getX(), 0);
        } else {
            Vup = Ay < Az ? new Vector(ray.getDir().getZ(), 0, -ray.getDir().getX()) :
                    new Vector(-ray.getDir().getY(), ray.getDir().getX(), 0);
        }

        Vector Vright = Vup.crossProduct(ray.getDir()).normalize();
        Point pc = ray.getPoint(DISTANCE);
        double step = glossy / sizeGrid;
        Point pij = pc.add(Vright.scale(numOfRowCol / 2 * -step)).add(Vup.scale(numOfRowCol / 2 * -step));
        Vector tempRayVector;
        Point Pij1;

        List<Ray> rays = new ArrayList<>();
        rays.add(ray);

        for (int i = 1; i < numOfRowCol; i++) {
            for (int j = 1; j < numOfRowCol; j++) {
                Pij1 = pij.add(Vright.scale(i * step)).add(Vup.scale(j * step));
                tempRayVector = Pij1.subtract(ray.getP0());

                if (n.dotProduct(tempRayVector) < 0 && direction == 1) { // refraction
                    rays.add(new Ray(ray.getP0(), tempRayVector));
                }

                if (n.dotProduct(tempRayVector) > 0 && direction == -1) { // reflection
                    rays.add(new Ray(ray.getP0(), tempRayVector));
                }
            }
        }

        return rays;
    }

    /**
     * Sets the parameters for promoting glossy rays in the ray tracer.
     *
     * @param num_rays   The number of rays.
     * @param distance   The distance parameter.
     * @param size       The size of the grid.
     * @return The updated RayTracerBasic object.
     */
    public RayTracerBasic set_Glossy_promoted(double num_rays, double distance, double size) {
        NUM_OF_RAYS = num_rays;
        DISTANCE = distance;
        sizeGrid = size;
        return this;
    }
    /**
     * Calculates the color for a given intersection point, taking into account local effects (diffuse and specular reflection).
     *
     * @param gp The intersection point
     * @param ray The ray being traced
     * @param k The attenuation factor
     * @return The color with local effects
     */
    /**
     * Calculates the color at the intersection point between the ray and the geometry, considering global effects
     * such as reflection and refraction.
     *
     * @param gp    the intersection point between the ray and the geometry
     * @param ray   the ray being traced
     * @param level the recursion level
     * @param k     the accumulated transparency coefficient
     * @return the color at the intersection point, considering global effects
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        if (level == 1)
            return color;
        return color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * Calculates the color at the intersection point between the ray and the geometry, without considering global effects.
     *
     * @param gp  the intersection point between the ray and the geometry
     * @param ray the ray being traced
     * @return the color at the intersection point, without global effects
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(1)).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color at the intersection point between the ray and the geometry, considering local effects
     * such as diffuse and specular reflection.
     *
     * @param gp  the intersection point between the ray and the geometry
     * @param ray the ray being traced
     * @param k   the accumulated transparency coefficient
     * @return the color at the intersection point, considering local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K))
                    if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
                        Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                        color = color.add(iL.scale(calcDiffusive(material, nl)),
                                iL.scale(calcSpecular(material, n, l, nl, v)));
                    }
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse reflection factor for a given material and the dot product of the normal and light vectors.
     *
     * @param mat The material of the geometry
     * @param nl  The dot product of the normal and light vectors
     * @return The diffuse reflection factor
     */
    private Double3 calcDiffusive(Material mat, double nl)
    {
        if (nl<0)
            return mat.kD.scale(-nl);
        return mat.kD.scale(nl);
    }


    /**
     * Calculates the specular reflection factor for a given material, normal vector, light vector, and view vector.
     *
     * @param mat The material of the geometry
     * @param n   The surface normal vector
     * @param l   The light vector
     * @param nl  The dot product of the normal and light vectors
     * @param v   The view vector
     * @return The specular reflection factor
     */
    private Double3 calcSpecular(Material mat, Vector n,Vector l,double nl,Vector v)
    {
        Vector r=l.subtract(n.scale(2*nl));
        return mat.kS.scale(pow(Math.max(0,-v.dotProduct(r)),mat.nShininess));
    }

    /**
     * Finds the closest intersection between a given ray and the scene's geometries.
     *
     * @param ray The ray to be traced
     * @return The closest intersection point, or null if no intersection is found
     */
    private Intersectable.GeoPoint findClosestIntersection(Ray ray){
        List<Intersectable.GeoPoint> Intersection= scene.geometries.findGeoIntersections(ray);
        if (Intersection==null)
            return null;
        return ray.findClosestGeoPoint(Intersection);
    }
    private static final double DELTA = 0.1;

    /**
     * Checks if a point on a geometry is unshaded by other geometries between it and the light source.
     *
     * @param gp       The intersection point on the geometry
     * @param light    The light source
     * @param l        The light vector
     * @param n        The surface normal vector
     * @param nl       The dot product of the normal and light vectors
     * @return true if the point is unshaded, false otherwise
     */
    private boolean unshaded(GeoPoint gp,  LightSource light, Vector l, Vector n, double nl) {
        if(gp.geometry.getMaterial().kT!=Double3.ZERO)
            return true;
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection,n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        //if there are points in the intersections list that are closer to the point
        // than light source – return false
        //otherwise – return true
        GeoPoint p = intersections.get(0);
        int sizeIntersection = intersections.size();
        double distanceFromLight = light.getDistance(gp.point);
        for (GeoPoint intersection: intersections) {
            if (gp.point.distance(p.point)>distanceFromLight) sizeIntersection--;
        }
        return sizeIntersection==0;
    }
    /**
     * Calculates the transparency factor for a given intersection point, light source, light vector, and surface normal.
     *
     * @param gp     The intersection point
     * @param light  The light source
     * @param l      The light vector
     * @param n      The surface normal vector
     * @return The transparency factor
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        Point point = lightRay.getP0();
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        double distanceFromLight = light.getDistance(gp.point);
        for (GeoPoint element : intersections) {
            if (distanceFromLight > point.distance(element.point))
                ktr = ktr.product(element.geometry.getMaterial().kT);
        }
        return ktr;
    }
    /**
     * Traces a beam of rays and returns the average color of the traced rays.
     *
     * @param beam the list of rays in the beam
     * @return the average color of the traced rays
     */
    @Override
    public Color traceBeamRay(List<Ray> beam) {
        Color tmp = Color.BLACK;
        for (Ray ray:
                beam) {
            Color color = traceRay(ray);
            tmp = tmp.add(color);
        }
        return tmp.reduce(beam.size());
    }

    /**
     * Checks the color of the pixel with the help of individual rays and averages between
     * them and only if necessary continues to send beams of rays in recursion
     * @param centerP center pixl
     * @param Width Length
     * @param Height width
     * @param minWidth min Width
     * @param minHeight min Height
     * @param cameraLoc Camera location
     * @param Vright Vector right
     * @param Vup vector up
     * @param prePoints pre Points
     * @return Pixel color
     */
    @Override
    public Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints) {
        if (Width < minWidth * 2 || Height < minHeight * 2) {
            return this.traceRay(new Ray(cameraLoc, centerP.subtract(cameraLoc))) ;
        }

        List<Point> nextCenterPList = new LinkedList<>();
        List<Point> cornersList = new LinkedList<>();
        List<primitives.Color> colorList = new LinkedList<>();
        Point tempCorner;
        Ray tempRay;
        for (int i = -1; i <= 1; i += 2){
            for (int j = -1; j <= 1; j += 2) {
                tempCorner = centerP.add(Vright.scale(i * Width / 2)).add(Vup.scale(j * Height / 2));
                cornersList.add(tempCorner);
                if (prePoints == null || !isInList(prePoints, tempCorner)) {
                    tempRay = new Ray(cameraLoc, tempCorner.subtract(cameraLoc));
                    nextCenterPList.add(centerP.add(Vright.scale(i * Width / 4)).add(Vup.scale(j * Height / 4)));
                    colorList.add(traceRay(tempRay));
                }
            }
        }


        if (nextCenterPList == null || nextCenterPList.size() == 0) {
            return primitives.Color.BLACK;
        }


        boolean isAllEquals = true;
        primitives.Color tempColor = colorList.get(0);
        for (primitives.Color color : colorList) {
            if (!tempColor.isAlmostEquals(color))
                isAllEquals = false;
        }
        if (isAllEquals && colorList.size() > 1)
            return tempColor;


        tempColor = primitives.Color.BLACK;
        for (Point center : nextCenterPList) {
            tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cameraLoc, Vright, Vup, cornersList));
        }
        return tempColor.reduce(nextCenterPList.size());


    }

    /**
     * Find a point in the list
     * @param pointsList the list
     * @param point the point that we look for
     * @return
     */
    private boolean isInList(List<Point> pointsList, Point point) {
        for (Point tempPoint : pointsList) {
            if(point.equals(tempPoint))
                return true;
        }
        return false;
    }
}



