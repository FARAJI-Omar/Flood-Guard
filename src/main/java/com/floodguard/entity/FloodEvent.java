package com.floodguard.entity;

import com.floodguard.enums.Severity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.MultiPolygon;

import java.time.LocalDate;

@Entity
@Table(name = "flood_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FloodEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Column(name = "area_km2")
    private Double areaKm2;

    /**
     * 'UNOSAT' | 'CopernicusEMS'
     */
    private String source;

    @Column(columnDefinition = "geometry(MultiPolygon, 4326)")
    private MultiPolygon geom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
