package com.floodguard.service.impl;

import com.floodguard.dto.request.SimulationRequest;
import com.floodguard.dto.response.SimulationResponse;
import com.floodguard.entity.Simulation;
import com.floodguard.entity.User;
import com.floodguard.enums.LandCover;
import com.floodguard.enums.RiskLevel;
import com.floodguard.enums.Role;
import com.floodguard.enums.SoilClass;
import com.floodguard.exception.SimulationNotFoundException;
import com.floodguard.exception.UserNotFoundException;
import com.floodguard.repository.SimulationRepository;
import com.floodguard.repository.UserRepository;
import com.floodguard.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SimulationServiceImpl implements SimulationService {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);
    // private static final String SIMULATION_DISCLAIMER = "MVP soil-only simulation: estimation indicative basée sur des paramètres génériques. Ne constitue pas une modélisation hydrologique validée.";

    private final SimulationRepository simulationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SimulationResponse create(SimulationRequest request) {
        User user = getCurrentUser();
        SoilClass soilClass = resolveSoilClass(request);
        LandCover landCover = resolveLandCover(request);
        double cn = computeCn(soilClass, landCover);
        double runoffMm = computeRunoffMm(request.getRainfallMm(), cn);
        double infiltrationMm = computeInfiltrationMm(request.getRainfallMm(), runoffMm);
        RiskLevel riskLevel = deriveRiskLevel(cn, request.getRainfallMm());
        Polygon polygon = toPolygon(request.getCoordinates());
        double areaKm2 = computeAreaKm2(polygon);
        String resultGeojson = buildResultGeojson(request.getCoordinates(), runoffMm, riskLevel);

        Simulation simulation = Simulation.builder()
                .user(user)
                .rainfallMm(request.getRainfallMm())
                .soilClass(soilClass)
                .landCover(landCover)
                .cnValue(cn)
                .runoffMm(runoffMm)
                .infiltrationMm(infiltrationMm)
                .riskLevel(riskLevel)
                .resultGeojson(resultGeojson)
                .zoneGeom(polygon)
                // .disclaimer(SIMULATION_DISCLAIMER)
                .build();

        Simulation saved = simulationRepository.save(simulation);
        return toResponse(saved);
    }

    @Override
    public List<SimulationResponse> getMine() {
        User user = getCurrentUser();
        return simulationRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<SimulationResponse> getHistory(Long userId) {
        List<Simulation> simulations = userId == null
                ? simulationRepository.findAllByOrderByCreatedAtDesc()
                : simulationRepository.findByUserIdOrderByCreatedAtDesc(userId);

        return simulations.stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public SimulationResponse getById(Long id) {
        User currentUser = getCurrentUser();
        Simulation simulation = simulationRepository.findById(id)
                .orElseThrow(() -> new SimulationNotFoundException(id));

        if (!isAdmin(currentUser) && !simulation.getUser().getId().equals(currentUser.getId())) {
            throw new SimulationNotFoundException(id);
        }

        return toResponse(simulation);
    }

    private static boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found"));
    }

    private static Polygon toPolygon(List<List<Double>> ring) {
        if (ring == null || ring.size() < 3) {
            throw new IllegalArgumentException("Polygon coordinates must contain at least 3 points.");
        }

        for (List<Double> point : ring) {
            if (point == null || point.size() < 2 || point.get(0) == null || point.get(1) == null) {
                throw new IllegalArgumentException("Each coordinate must be [lng, lat].");
            }
        }

        List<List<Double>> closed = ring;
        List<Double> first = ring.get(0);
        List<Double> last = ring.get(ring.size() - 1);

        if (!first.get(0).equals(last.get(0)) || !first.get(1).equals(last.get(1))) {
            closed = new ArrayList<>(ring);
            closed.add(first);
        }

        Coordinate[] coords = closed.stream()
                .map(p -> new Coordinate(p.get(0), p.get(1)))
                .toArray(Coordinate[]::new);

        LinearRing linearRing = GEOMETRY_FACTORY.createLinearRing(coords);
        return GEOMETRY_FACTORY.createPolygon(linearRing);
    }

    private static SoilClass resolveSoilClass(SimulationRequest request) {
        return request.getSoilClass();
    }

    private static LandCover resolveLandCover(SimulationRequest request) {
        return request.getLandCover();
    }

    private static double computeCn(SoilClass soilClass, LandCover landCover) {
        return switch (landCover) {
            case URBAN_DENSE -> switch (soilClass) {
                case A -> 89.0;
                case B -> 92.0;
                case C -> 94.0;
                case D -> 95.0;
            };
            case URBAN_SPARSE -> switch (soilClass) {
                case A -> 77.0;
                case B -> 85.0;
                case C -> 90.0;
                case D -> 92.0;
            };
            case VEGETATION -> switch (soilClass) {
                case A -> 39.0;
                case B -> 61.0;
                case C -> 74.0;
                case D -> 80.0;
            };
            case BARE_SOIL -> switch (soilClass) {
                case A -> 62.0;
                case B -> 76.0;
                case C -> 84.0;
                case D -> 87.0;
            };
        };
    }

    private static double computeRunoffMm(double rainfallMm, double cn) {
        double s = (25400.0 / cn) - 254.0;
        double ia = 0.2 * s;
        if (rainfallMm <= ia) {
            return 0.0;
        }
        double numerator = Math.pow(rainfallMm - ia, 2);
        double denominator = rainfallMm - ia + s;
        return numerator / denominator;
    }

    private static double computeInfiltrationMm(double rainfallMm, double runoffMm) {
        return rainfallMm - runoffMm;
    }

    private static double computeAreaKm2(Polygon polygon) {
        double area = polygon.getArea(); // This gives area in degrees²
        
        // Convert from degrees² to km² using approximate conversion at Morocco's latitude (~32°N)
        // At 32°N: 1 degree latitude ≈ 111 km, 1 degree longitude ≈ 94 km
        double latitudeFactor = 111.0; // km per degree latitude
        double longitudeFactor = 94.0; // km per degree longitude at ~32°N
        
        // Approximate area in km²
        double areaKm2 = area * latitudeFactor * longitudeFactor;
        
        // Round to 2 decimal places
        return Math.round(areaKm2 * 100.0) / 100.0;
    }

    private static RiskLevel deriveRiskLevel(double cn, double rainfallMm) {
        if (rainfallMm < 10) return RiskLevel.LOW;
        if (rainfallMm < 30) {
            if (cn >= 85) return RiskLevel.MODERATE;
            return RiskLevel.LOW;
        }
        if (rainfallMm < 60) {
            if (cn >= 85) return RiskLevel.HIGH;
            if (cn >= 70) return RiskLevel.MODERATE;
            return RiskLevel.LOW;
        }
        // rainfallMm >= 60
        if (cn >= 85) return RiskLevel.CRITICAL;
        if (cn >= 70) return RiskLevel.HIGH;
        if (cn >= 50) return RiskLevel.MODERATE;
        return RiskLevel.LOW;
    }

    private static String buildResultGeojson(List<List<Double>> ring, double runoffMm, RiskLevel riskLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"Feature\",\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[");
        for (int i = 0; i < ring.size(); i++) {
            List<Double> p = ring.get(i);
            sb.append("[").append(p.get(0)).append(",").append(p.get(1)).append("]");
            if (i < ring.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]]}");
        sb.append(",\"properties\":{\"runoffMm\":").append(runoffMm)
                .append(",\"riskLevel\":\"").append(riskLevel).append("\"}}");
        return sb.toString();
    }

    private SimulationResponse toResponse(Simulation simulation) {
        return SimulationResponse.builder()
                .id(simulation.getId())
                .createdAt(simulation.getCreatedAt())
                .userId(simulation.getUser().getId())
                // .userEmail(simulation.getUser().getEmail())
                .username(simulation.getUser().getUsername())
                .rainfallMm(simulation.getRainfallMm())
                .soilClass(simulation.getSoilClass())
                .landCover(simulation.getLandCover())
                .cnValue(simulation.getCnValue())
                .runoffMm(simulation.getRunoffMm())
                .infiltrationMm(simulation.getInfiltrationMm())
                .areaKm2(computeAreaKm2(simulation.getZoneGeom()))
                .riskLevel(simulation.getRiskLevel())
                .resultGeojson(simulation.getResultGeojson())
                // .disclaimer(simulation.getDisclaimer())
                .build();
    }
}
