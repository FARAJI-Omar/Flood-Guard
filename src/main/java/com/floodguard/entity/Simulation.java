package com.floodguard.entity;

import com.floodguard.enums.LandCover;
import com.floodguard.enums.RiskLevel;
import com.floodguard.enums.SoilClass;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Polygon;

import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "rainfall_mm", nullable = false)
    private Double rainfallMm;

    @Enumerated(EnumType.STRING)
    @Column(name = "soil_class", nullable = false)
    private SoilClass soilClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "land_cover", nullable = false)
    private LandCover landCover;

    @Column(name = "cn_value")
    private Double cnValue;

    @Column(name = "runoff_mm")
    private Double runoffMm;

    @Column(name = "infiltration_mm")
    private Double infiltrationMm;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_level")
    private RiskLevel riskLevel;

    @Column(name = "result_geojson", columnDefinition = "TEXT")
    private String resultGeojson;

    @Column(name = "disclaimer", columnDefinition = "TEXT")
    private String disclaimer;

    @Column(name = "zone_geom", columnDefinition = "geometry(Polygon, 4326)")
    private Polygon zoneGeom;
}
