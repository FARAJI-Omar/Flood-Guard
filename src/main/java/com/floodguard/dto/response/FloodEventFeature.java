package com.floodguard.dto.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single GeoJSON Feature for a flood event.
 * Structure:
 * {
 * "type": "Feature",
 * "geometry": { "type": "MultiPolygon", "coordinates": [...] },
 * "properties": { "id": 1, "name": "...", "severity": "high", ... }
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloodEventFeature {

    private final String type = "Feature";
    /**
     * Embedded as raw JSON so Leaflet receives a proper GeoJSON geometry object.
     */
    @JsonRawValue
    private String geometry;
    private FloodEventProperties properties;
}
