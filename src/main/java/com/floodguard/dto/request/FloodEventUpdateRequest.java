package com.floodguard.dto.request;

import com.floodguard.enums.Severity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FloodEventUpdateRequest {

    private String name;

    private LocalDate eventDate;

    private Severity severity;

    private String source;

    /**
     * GeoJSON Polygon ring coordinates from Leaflet Draw.
     * Format: [[lng, lat], [lng, lat], ..., [lng, lat]]
     */
    private List<List<Double>> coordinates;
}
