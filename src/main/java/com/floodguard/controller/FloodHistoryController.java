package com.floodguard.controller;

import com.floodguard.dto.request.FloodEventRequest;
import com.floodguard.dto.request.FloodEventUpdateRequest;
import com.floodguard.dto.response.FloodEventCollectionResponse;
import com.floodguard.dto.response.FloodEventFeature;
import com.floodguard.enums.Severity;
import com.floodguard.service.FloodEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/floodevents")
@RequiredArgsConstructor
public class FloodHistoryController {

    private final FloodEventService floodEventService;


    /**
     * Returns all flood history events as a GeoJSON FeatureCollection.
     * Optional filters: year (int) and severity (string)
     * ('LOW'|'MODERATE'|'HIGH'|'CRITICAL')
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<FloodEventCollectionResponse> getAll(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Severity severity) {
        return ResponseEntity.ok(floodEventService.getAll(year, severity));
    }

    /**
     * Returns a single flood event as a GeoJSON Feature.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<FloodEventFeature> getById(@PathVariable Long id) {
        return ResponseEntity.ok(floodEventService.getById(id));
    }



    /**
     * Create a new flood event.
     * Body: { name, eventDate, severity, areaKm2, source,
     * coordinates: [[lng,lat], ...] } ← Leaflet Draw output
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FloodEventFeature> create(
            @RequestBody @Valid FloodEventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(floodEventService.create(request));
    }

    /**
     * Update an existing flood event (partial update).
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FloodEventFeature> update(
            @PathVariable Long id,
            @RequestBody @Valid FloodEventUpdateRequest request) {
        return ResponseEntity.ok(floodEventService.update(id, request));
    }

    /**
     * Delete a flood event by ID.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        floodEventService.delete(id);
        return ResponseEntity.ok("flood Event with ID: " + id + " deleted successfully.");
    }
}
