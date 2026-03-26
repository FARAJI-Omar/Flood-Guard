package com.floodguard.dto.request;

import com.floodguard.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FloodEventRequest {

    @NotBlank
    private String name;

    @NotNull
    private LocalDate eventDate;

    @NotNull
    private Severity severity;

    private String source;

    /**
     * GeoJSON Polygon ring coordinates from Leaflet Draw.
     * Format: [[lng, lat], [lng, lat], ..., [lng, lat]]
     * Pass feature.geometry.coordinates[0] directly from Leaflet.
     * Ring will be auto-closed server-side if needed.
     */
    @NotEmpty
    private List<List<Double>> coordinates;
}
