package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    public   List<GeoPoint>findGeoIntersections(Ray ray)
    { return findGeoIntersectionsHelper(ray);}
    protected abstract List<GeoPoint>findGeoIntersectionsHelper(Ray ray);
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GeoPoint geoPoint = (GeoPoint) o;

            if (!Objects.equals(geometry, geoPoint.geometry)) return false;
            return Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

}
