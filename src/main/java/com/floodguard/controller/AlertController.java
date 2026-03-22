package com.floodguard.controller;

import com.floodguard.dto.request.AlertRequest;
import com.floodguard.dto.request.ThresholdRequest;
import com.floodguard.dto.response.AlertResponse;
import com.floodguard.dto.response.ThresholdResponse;
import com.floodguard.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping("/threshold")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ThresholdResponse> getThreshold() {
        return ResponseEntity.ok(alertService.getThreshold());
    }

    @PutMapping("/threshold")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ThresholdResponse> updateThreshold(@RequestBody @Valid ThresholdRequest request) {
        return ResponseEntity.ok(alertService.updateThreshold(request));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<AlertResponse> createAlert(@RequestBody @Valid AlertRequest request) {
        return ResponseEntity.ok(alertService.createAlert(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Page<AlertResponse>> getAllAlerts(Pageable pageable) {
        return ResponseEntity.ok(alertService.getAllAlerts(pageable));
    }
}
