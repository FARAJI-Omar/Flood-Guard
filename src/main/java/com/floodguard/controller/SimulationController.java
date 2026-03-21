package com.floodguard.controller;

import com.floodguard.dto.request.SimulationRequest;
import com.floodguard.dto.response.SimulationResponse;
import com.floodguard.service.SimulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulations")
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

   // endpoint to run a new simulation based on user input parameters.
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<SimulationResponse> create(
            @RequestBody @Valid SimulationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(simulationService.create(request));
    }

    // tracking endpoint: returns simulations history for the current user.
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<SimulationResponse>> getMine() {
        return ResponseEntity.ok(simulationService.getMine());
    }

    
    //   tracking endpoint: returns simulations history for all users.
     
    @GetMapping("/history")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SimulationResponse>> getHistory(
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(simulationService.getHistory(userId));
    }

    // tracking endpoint: returns details of a specific simulation by ID.
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SimulationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(simulationService.getById(id));
    }
}
