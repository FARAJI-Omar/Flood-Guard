package com.floodguard.dto.response;

import com.floodguard.enums.LandCover;
import com.floodguard.enums.RiskLevel;
import com.floodguard.enums.SoilClass;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SimulationResponse {
    private Long id;
    private LocalDateTime createdAt;
    private Long userId;
    // private String userEmail;
    private String username;

    private Double rainfallMm;
    private SoilClass soilClass;
    private LandCover landCover;

    private Double cnValue;
    private Double runoffMm;
    private Double infiltrationMm;
    private Double areaKm2;
    private RiskLevel riskLevel;
    private String resultGeojson;
    // private String disclaimer;
}
