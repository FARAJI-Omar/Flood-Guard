package com.floodguard.mapper;

import com.floodguard.dto.response.FloodEventFeature;
import com.floodguard.dto.response.FloodEventProperties;
import com.floodguard.entity.FloodEvent;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FloodEventMapper {

    @Mapping(target = "geometry", expression = "java(multiPolygonToGeoJson(event.getGeom()))")
    @Mapping(target = "properties", source = "event")
    FloodEventFeature toFeature(FloodEvent event);

    FloodEventProperties toProperties(FloodEvent event);

    /**
     * Converts a JTS MultiPolygon to a GeoJSON geometry string using only jts-core.
     */
    default String multiPolygonToGeoJson(MultiPolygon geom) {
        if (geom == null)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"MultiPolygon\",\"coordinates\":[");
        for (int i = 0; i < geom.getNumGeometries(); i++) {
            if (i > 0)
                sb.append(",");
            Polygon polygon = (Polygon) geom.getGeometryN(i);
            sb.append("[");
            appendRing(sb, polygon.getExteriorRing().getCoordinates());
            for (int h = 0; h < polygon.getNumInteriorRing(); h++) {
                sb.append(",");
                appendRing(sb, polygon.getInteriorRingN(h).getCoordinates());
            }
            sb.append("]");
        }
        sb.append("]}");
        return sb.toString();
    }

    private static void appendRing(StringBuilder sb, Coordinate[] coords) {
        sb.append("[");
        for (int j = 0; j < coords.length; j++) {
            if (j > 0)
                sb.append(",");
            sb.append("[").append(coords[j].x).append(",").append(coords[j].y).append("]");
        }
        sb.append("]");
    }
}
