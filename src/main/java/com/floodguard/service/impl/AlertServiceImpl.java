package com.floodguard.service.impl;

import com.floodguard.dto.request.AlertRequest;
import com.floodguard.dto.request.ThresholdRequest;
import com.floodguard.dto.response.AlertResponse;
import com.floodguard.dto.response.ThresholdResponse;
import com.floodguard.entity.Alert;
import com.floodguard.entity.AlertThreshold;
import com.floodguard.enums.Severity;
import com.floodguard.exception.ThresholdNotFoundException;
import com.floodguard.repository.AlertRepository;
import com.floodguard.repository.AlertThresholdRepository;
import com.floodguard.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final AlertThresholdRepository thresholdRepository;

    @Override
    public ThresholdResponse getThreshold() {
        AlertThreshold threshold = thresholdRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ThresholdNotFoundException("Threshold not configured"));
        return new ThresholdResponse(threshold.getValue());
    }

    @Override
    @Transactional
    public ThresholdResponse updateThreshold(ThresholdRequest request) {
        AlertThreshold threshold = thresholdRepository.findAll().stream()
                .findFirst()
                .orElse(new AlertThreshold());
        threshold.setValue(request.getValue());
        thresholdRepository.save(threshold);
        return new ThresholdResponse(threshold.getValue());
    }

    @Override
    @Transactional
    public AlertResponse createAlert(AlertRequest request) {
        AlertThreshold thresholdEntity = thresholdRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ThresholdNotFoundException(
                        "Threshold not configured. Please configure threshold first."));

        Double thresholdValue = thresholdEntity.getValue();
        Severity severity = calculateSeverity(request.getPrecipitation(), thresholdValue);

        Alert alert = Alert.builder()
                .cityName(request.getCityName())
                .precipitation(request.getPrecipitation())
                .threshold(thresholdValue)
                .timestamp(LocalDateTime.now())
                .severity(severity)
                .status("active")
                .build();

        Alert saved = alertRepository.save(alert);
        return mapToResponse(saved);
    }

    @Override
    public Page<AlertResponse> getAllAlerts(Pageable pageable) {
        return alertRepository.findAll(pageable).map(this::mapToResponse);
    }

    private Severity calculateSeverity(Double precipitation, Double threshold) {
        if (precipitation >= threshold * 2) {
            return Severity.DANGER;
        }
        return Severity.WARNING;
    }

    private AlertResponse mapToResponse(Alert alert) {
        return AlertResponse.builder()
                .id(alert.getId())
                .cityName(alert.getCityName())
                .precipitation(alert.getPrecipitation())
                .threshold(alert.getThreshold())
                .timestamp(alert.getTimestamp())
                .severity(alert.getSeverity())
                .status(alert.getStatus())
                .build();
    }
}
