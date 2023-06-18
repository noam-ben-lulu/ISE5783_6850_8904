/**
 * The Camera class represents a virtual camera in a 3D environment, used to capture images.
 */
package renderer;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {

    /** The camera's location in 3D space */
    private Point p0;

    /** The camera's up vector */
    private Vector vUp;

    /** The camera's direction vector */
    private Vector vTo;

    /** The camera's right vector, derived from the up and direction vectors */
    private Vector vRight;

    /** The width of the viewport */
    private double width;

    /** The height of the viewport */
    private double height;

    /** The distance between the camera and the viewport */
    private double distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * Sets the  imageWriter
     *  @return The Camera object
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }
    /**
     * Sets the  rayTracerBasic
     *  @return The Camera object
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer =rayTracer;
        return this;
    }
    /**
     * The function is color the image by construct rays to the fixel and calculus the color
     */
    public ImageWriter renderImage(){
        if (this.rayTracer == null || this.imageWriter == null || this.width == 0 || this.height == 0 || this.distance == 0)
            throw new UnsupportedOperationException("MissingResourcesException");

       // List<Ray> ray;
        Ray ray;
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

      //  for (int i = 0; i < nX; i++) {
           // for (int j = 0; j < nY; j++) {
             //   ray = constructRay(nX,nY,i,j);
             // Color color = rayTracer.traceRay(ray);

                // The anti-Analzing improvment
                //Color color = rayTracer.traceBeamRay(constructRayBeam(j,i, imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
              //  imageWriter.writePixel(i, j, color);
          //  }
       // }
        Pixel.initialize(nY, nX, 0);
        IntStream.range(0, nY).parallel().forEach(i -> {
            IntStream.range(0, nX).parallel().forEach(j -> {
                imageWriter.writePixel(j, i,castRay(nX, nY, j, i));
                Pixel.pixelDone();
                Pixel.printPixel();
            });
        });
        return imageWriter;
    }
    /**
     * Creat a grid to the image
     */

    public void printGrid(int interval, Color color)
    {
        if (imageWriter == null) throw new UnsupportedOperationException("MissingResourcesException");

        for (int i = 0; i < imageWriter.getNx(); i+=interval) {
            for (int j = 0; j < imageWriter.getNy(); j+=1) {
                imageWriter.writePixel(i, j, new Color(color.getColor()));
            }
        }
        for (int i = 0; i < imageWriter.getNy(); i+=interval) {
            for (int j = 0; j < imageWriter.getNx(); j+=1) {
                imageWriter.writePixel(j, i, new Color(color.getColor()));
            }
        }
    }
    /**
     send to the function that appear at imageWriter
     */
    public void writeToImage()
    {
        imageWriter.writeToImage();
    }
    /**
     * Creates a new Camera object.
     * @param p0 The camera's location in 3D space
     * @param vUp The camera's up vector
     * @param vTo The camera's direction vector
     * @throws IllegalArgumentException if the up and direction vectors are perpendicular (dot product equals 0)
     */
    public Camera(Point p0, Vector vTo, Vector vUp)
    {
        this.p0 = p0;
        this.vUp = vUp;
        this.vTo = vTo;

        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("Up and direction vectors are not orthogonal (dot product not equals 0)");
        this.vRight = vTo.crossProduct(vUp).normalize();
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
    }

    /**
     * Sets the size of the viewport.
     * @param width The width of the viewport
     * @param height The height of the viewport
     * @return The Camera object
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the distance between the camera and the viewport.
     * @param distance The distance between the camera and the viewport
     * @return The Camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Constructs a ray from the camera through the specified pixel in the viewport.
     * @param nX The number of pixels along the x-axis
     * @param nY The number of pixels along the y-axis
     * @param j The index of the pixel along the x-axis
     * @param i The index of the pixel along the y-axis
     * @return The Ray object that goes through the specified pixel
     */
    public Ray constructRay(int nX, int nY, double j, double i) {
        Point  Pc=this.p0.add(vTo.scale(this.distance));
        double Ry=this.height/nY;
        double Rx=this.width/nX;
        double Yi=-(i-(double)(nY-1)/2)*Ry;
        double Xj= (j-(double)(nX-1)/2)*Rx;
        Point Pij = Pc;
        if (Xj != 0) Pij = Pij.add(vRight.scale(Xj));
        if (Yi != 0) Pij = Pij.add(vUp.scale(Yi));
        //Point Pij=Pc.add((vRight.scale(Xj)).add(vUp.scale(Yi)));
        Vector Vij=Pij.subtract(this.p0);
        return new Ray(this.p0,Vij);
    }
    private Color castRay(int nX, int nY, int i, int j) {
        return rayTracer.traceRay(constructRay(nX, nY, i, j));
    }
    /**
     * Gets the center point of a pixel in the viewport.
     *
     * @param i           The index of the pixel along the y-axis
     * @param j           The index of the pixel along the x-axis
     * @param nX          The number of pixels along the x-axis
     * @param nY          The number of pixels along the y-axis
     * @param pixelHeight The height of a pixel
     * @param pixelWidth  The width of a pixel
     * @return The center point of the specified pixel
     */
    private Point getCenterOfPixel(int i, int j, int nX, int nY, double pixelHeight, double pixelWidth) {
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        // Xj = (j - (Nx -1)/2) * Rx
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX);
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(-(i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = this.p0;

        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }
        return pIJ;
    }

    /**
     * Constructs a ray within a pixel in the viewport.
     *
     * @param nX         The number of pixels along the x-axis
     * @param nY         The number of pixels along the y-axis
     * @param j          The index of the pixel along the x-axis
     * @param i          The index of the pixel along the y-axis
     * @param center     The center point of the pixel
     * @param gridWidth  The width of the grid
     * @param gridHeight The height of the grid
     * @return The constructed ray within the specified pixel
     */
    private Ray constructRayInPixel(int nX, int nY, int j, int i, Point center, int gridWidth, int gridHeight) {
        Point pij = center;
        double yi = -(i - ((double) gridHeight - 1) / 2) * (height / nY) / gridHeight;
        if (yi != 0) pij = pij.add(vUp.scale(yi));
        double xj = (j - ((double) gridWidth - 1) / 2) * (width / nX) / gridWidth;
        if (xj != 0) pij = pij.add(vRight.scale(xj));
        return new Ray(p0, pij.subtract(p0));
    }

    /**
     * Constructs a beam of rays within a group of pixels in the viewport.
     *
     * @param i           The index of the starting pixel along the y-axis
     * @param j           The index of the starting pixel along the x-axis
     * @param nX          The number of pixels along the x-axis
     * @param nY          The number of pixels along the y-axis
     * @param gridWidth   The width of the grid
     * @param gridHighet  The height of the grid
     * @param pixelHighet The height of a pixel
     * @param pixelWidth  The width of a pixel
     * @return The list of rays within the specified group of pixels
     */
    private List<Ray> constructRayBeam(int i, int j, int nX, int nY, int gridWidth, int gridHighet, double pixelHighet, double pixelWidth)
    {
        List<Ray> beam = new ArrayList<>();
        Point center = getCenterOfPixel(i,j,nX,nY, pixelHighet, pixelWidth);
        for (int i1=0;i1<gridHighet;i1++)
        {
            for (int j1=0;j1<gridWidth;j1++)
            {
                beam.add(constructRayInPixel(nX, nY,j1,i1,center,gridWidth,gridHighet));
            }
        }
        return beam;
    }


    /**
     * Gets the width of the viewport.
     * @return The width of the viewport
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the viewport.
     * @return The height of the viewport
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the distance between the camera and the viewport.
     * @return The distance between the camera and the viewport
     */
    public double getDistance() {
        return distance;
    }


}