package com.floodguard.dto.request;

import com.floodguard.enums.LandCover;
import com.floodguard.enums.SoilClass;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SimulationRequest {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double rainfallMm;

    @NotNull
    private SoilClass soilClass;

    @NotNull
    private LandCover landCover;

    /**
     * GeoJSON Polygon ring coordinates from Leaflet Draw.
     * Format: [[lng, lat], [lng, lat], ..., [lng, lat]]
     */
    @NotEmpty
    private List<List<Double>> coordinates;
}
