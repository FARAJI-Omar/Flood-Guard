package com.floodguard.service.impl;

import com.floodguard.dto.request.FloodEventRequest;
import com.floodguard.dto.request.FloodEventUpdateRequest;
import com.floodguard.dto.response.FloodEventCollectionResponse;
import com.floodguard.dto.response.FloodEventFeature;
import com.floodguard.entity.FloodEvent;
import com.floodguard.enums.Severity;
import com.floodguard.exception.FloodEventNotFoundException;
import com.floodguard.mapper.FloodEventMapper;
import com.floodguard.repository.FloodEventRepository;
import com.floodguard.service.FloodEventService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FloodEventServiceImpl implements FloodEventService {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);

    private final FloodEventRepository floodEventRepository;
    private final FloodEventMapper floodEventMapper;


    @Override
    public FloodEventCollectionResponse getAll(Integer year, Severity severity) {
        String severityStr = severity != null ? severity.name() : null;
        List<FloodEvent> events = floodEventRepository.findByFilters(year, severityStr);
        List<FloodEventFeature> features = events.stream()
                .map(floodEventMapper::toFeature)
                .toList();
        return FloodEventCollectionResponse.builder()
                .features(features)
                .totalCount(features.size())
                .build();
    }

    @Override
    public FloodEventFeature getById(Long id) {
        FloodEvent event = floodEventRepository.findById(id)
                .orElseThrow(() -> new FloodEventNotFoundException(id));
        return floodEventMapper.toFeature(event);
    }


    @Override
    @Transactional
    public FloodEventFeature create(FloodEventRequest request) {
        MultiPolygon geom = toMultiPolygon(request.getCoordinates());
        FloodEvent event = FloodEvent.builder()
                .name(request.getName())
                .eventDate(request.getEventDate())
                .severity(request.getSeverity())
                .areaKm2(calculateAreaKm2(geom))
                .source(request.getSource())
                .geom(geom)
                .build();
        return floodEventMapper.toFeature(floodEventRepository.save(event));
    }

    @Override
    @Transactional
    public FloodEventFeature update(Long id, FloodEventUpdateRequest request) {
        FloodEvent event = floodEventRepository.findById(id)
                .orElseThrow(() -> new FloodEventNotFoundException(id));
        
        if (request.getName() != null) {
            event.setName(request.getName());
        }
        if (request.getEventDate() != null) {
            event.setEventDate(request.getEventDate());
        }
        if (request.getSeverity() != null) {
            event.setSeverity(request.getSeverity());
        }
        if (request.getSource() != null) {
            event.setSource(request.getSource());
        }
        if (request.getCoordinates() != null) {
            MultiPolygon geom = toMultiPolygon(request.getCoordinates());
            event.setGeom(geom);
            event.setAreaKm2(calculateAreaKm2(geom));
        }
        
        return floodEventMapper.toFeature(floodEventRepository.save(event));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!floodEventRepository.existsById(id)) {
            throw new FloodEventNotFoundException(id);
        }
        floodEventRepository.deleteById(id);
    }

    // Geometry conversion ***********************

    /**
     * Calculates the geodetic area of a MultiPolygon in square kilometers.
     * Uses spherical approximation for WGS84 coordinates.
     * Returns value rounded to 2 decimal places.
     */
    private static double calculateAreaKm2(MultiPolygon multiPolygon) {
        double totalAreaM2 = 0.0;
        for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
            Polygon polygon = (Polygon) multiPolygon.getGeometryN(i);
            totalAreaM2 += calculatePolygonAreaM2(polygon.getExteriorRing().getCoordinates());
        }
        double areaKm2 = totalAreaM2 / 1_000_000.0;
        return Math.round(areaKm2 * 100.0) / 100.0;
    }

    /**
     * Calculates polygon area using spherical excess formula.
     * Coordinates must be in WGS84 (longitude, latitude).
     */
    private static double calculatePolygonAreaM2(Coordinate[] coords) {
        final double EARTH_RADIUS_M = 6371000.0;
        double area = 0.0;
        int n = coords.length - 1;
        
        for (int i = 0; i < n; i++) {
            double lon1 = Math.toRadians(coords[i].x);
            double lat1 = Math.toRadians(coords[i].y);
            double lon2 = Math.toRadians(coords[i + 1].x);
            double lat2 = Math.toRadians(coords[i + 1].y);
            
            area += (lon2 - lon1) * (2 + Math.sin(lat1) + Math.sin(lat2));
        }
        
        return Math.abs(area * EARTH_RADIUS_M * EARTH_RADIUS_M / 2.0);
    }

    /**
     * Converts a Leaflet Draw polygon ring (List of [lng, lat] pairs) into a
     * JTS MultiPolygon suitable for PostGIS storage.
     * Automatically closes the ring if the first and last point differ.
     */
    private static MultiPolygon toMultiPolygon(List<List<Double>> ring) {
        List<List<Double>> closed = ring;
        List<Double> first = ring.get(0);
        List<Double> last = ring.get(ring.size() - 1);
        if (!first.get(0).equals(last.get(0)) || !first.get(1).equals(last.get(1))) {
            closed = new java.util.ArrayList<>(ring);
            closed.add(first);
        }
        Coordinate[] coords = closed.stream()
                .map(p -> new Coordinate(p.get(0), p.get(1)))
                .toArray(Coordinate[]::new);
        LinearRing linearRing = GEOMETRY_FACTORY.createLinearRing(coords);
        Polygon polygon = GEOMETRY_FACTORY.createPolygon(linearRing);
        return GEOMETRY_FACTORY.createMultiPolygon(new Polygon[] { polygon });
    }
}
