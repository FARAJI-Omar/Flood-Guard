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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceImplTest {

    @Mock
    private AlertRepository alertRepository;

    @Mock
    private AlertThresholdRepository thresholdRepository;

    @InjectMocks
    private AlertServiceImpl alertService;

    private AlertThreshold testThreshold;
    private Alert testAlert;
    private AlertRequest testAlertRequest;
    private ThresholdRequest testThresholdRequest;

    @BeforeEach
    void setUp() {
        testThreshold = AlertThreshold.builder()
                .id(1L)
                .value(50.0)
                .build();

        testAlert = Alert.builder()
                .id(1L)
                .cityName("London")
                .precipitation(60.0)
                .threshold(50.0)
                .timestamp(LocalDateTime.now())
                .severity(Severity.WARNING)
                .status("active")
                .build();

        testAlertRequest = new AlertRequest();
        testAlertRequest.setCityName("London");
        testAlertRequest.setPrecipitation(60.0);

        testThresholdRequest = new ThresholdRequest();
        testThresholdRequest.setValue(75.0);
    }

    // ==================== getThreshold() Tests ====================

    @Test
    void getThreshold_WhenThresholdExists_ReturnsThresholdResponse() {
        when(thresholdRepository.findAll()).thenReturn(List.of(testThreshold));

        ThresholdResponse response = alertService.getThreshold();

        assertNotNull(response);
        assertEquals(50.0, response.getValue());
        verify(thresholdRepository, times(1)).findAll();
    }

    @Test
    void getThreshold_WhenNoThresholdExists_ThrowsThresholdNotFoundException() {
        when(thresholdRepository.findAll()).thenReturn(Collections.emptyList());

        ThresholdNotFoundException exception = assertThrows(
                ThresholdNotFoundException.class,
                () -> alertService.getThreshold()
        );

        assertEquals("Threshold not configured", exception.getMessage());
        verify(thresholdRepository, times(1)).findAll();
    }

    @Test
    void getThreshold_WhenRepositoryReturnsNull_ThrowsThresholdNotFoundException() {
        when(thresholdRepository.findAll()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> alertService.getThreshold());
        verify(thresholdRepository, times(1)).findAll();
    }

    // ==================== updateThreshold() Tests ====================

    @Test
    void updateThreshold_WhenThresholdExists_UpdatesAndReturnsThresholdResponse() {
        when(thresholdRepository.findAll()).thenReturn(List.of(testThreshold));
        when(thresholdRepository.save(any(AlertThreshold.class))).thenReturn(testThreshold);

        ThresholdResponse response = alertService.updateThreshold(testThresholdRequest);

        assertNotNull(response);
        assertEquals(75.0, response.getValue());
        
        ArgumentCaptor<AlertThreshold> captor = ArgumentCaptor.forClass(AlertThreshold.class);
        verify(thresholdRepository, times(1)).save(captor.capture());
        assertEquals(75.0, captor.getValue().getValue());
    }

    @Test
    void updateThreshold_WhenNoThresholdExists_CreatesNewThreshold() {
        when(thresholdRepository.findAll()).thenReturn(Collections.emptyList());
        
        AlertThreshold newThreshold = new AlertThreshold();
        newThreshold.setValue(60.0);
        when(thresholdRepository.save(any(AlertThreshold.class))).thenReturn(newThreshold);

        testThresholdRequest.setValue(60.0);
        ThresholdResponse response = alertService.updateThreshold(testThresholdRequest);

        assertNotNull(response);
        assertEquals(60.0, response.getValue());
        verify(thresholdRepository, times(1)).save(any(AlertThreshold.class));
    }

    @Test
    void updateThreshold_WhenRequestIsNull_ThrowsException() {
        assertThrows(NullPointerException.class, () -> alertService.updateThreshold(null));
    }

    @Test
    void updateThreshold_WhenSaveFails_ThrowsException() {
        when(thresholdRepository.findAll()).thenReturn(List.of(testThreshold));
        when(thresholdRepository.save(any(AlertThreshold.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> alertService.updateThreshold(testThresholdRequest));
    }

    // ==================== createAlert() Tests ====================

    @Test
    void createAlert_WhenValidRequestAndThresholdExists_CreatesAlertWithWarningSeverity() {
        when(thresholdRepository.findAll()).thenReturn(List.of(testThreshold));
        when(alertRepository.save(any(Alert.class))).thenReturn(testAlert);

        AlertResponse response = alertService.createAlert(testAlertRequest);

        assertNotNull(response);
        assertEquals("London", response.getCityName());
        assertEquals(60.0, response.getPrecipitation());
        assertEquals(50.0, response.getThreshold());
        assertEquals(Severity.WARNING, response.getSeverity());
        assertEquals("active", response.getStatus());

        ArgumentCaptor<Alert> captor = ArgumentCaptor.forClass(Alert.class);
        verify(alertRepository, times(1)).save(captor.capture());
        assertEquals(Severity.WARNING, captor.getValue().getSeverity());
    }

    @Test
    void createAlert_WhenThresholdNotConfigured_ThrowsThresholdNotFoundException() {
        when(thresholdRepository.findAll()).thenReturn(Collections.emptyList());

        ThresholdNotFoundException exception = assertThrows(
                ThresholdNotFoundException.class,
                () -> alertService.createAlert(testAlertRequest)
        );

        assertEquals("Threshold not configured. Please configure threshold first.", exception.getMessage());
        verify(alertRepository, never()).save(any(Alert.class));
    }

    @Test
    void createAlert_WhenPrecipitationExceedsDoubleThreshold_CreatesDangerAlert() {
        testAlertRequest.setCityName("Paris");
        testAlertRequest.setPrecipitation(100.0);

        Alert dangerAlert = Alert.builder()
                .id(2L)
                .cityName("Paris")
                .precipitation(100.0)
                .threshold(50.0)
                .timestamp(LocalDateTime.now())
                .severity(Severity.DANGER)
                .status("active")
                .build();

        when(thresholdRepository.findAll()).thenReturn(List.of(testThreshold));
        when(alertRepository.save(any(Alert.class))).thenReturn(dangerAlert);

        AlertResponse response = alertService.createAlert(testAlertRequest);

        assertNotNull(response);
        assertEquals(Severity.DANGER, response.getSeverity());
        assertEquals(100.0, response.getPrecipitation());

        ArgumentCaptor<Alert> captor = ArgumentCaptor.forClass(Alert.class);
        verify(alertRepository, times(1)).save(captor.capture());
        assertEquals(Severity.DANGER, captor.getValue().getSeverity());
    }

    @Test
    void createAlert_WhenRepositorySaveFails_ThrowsException() {
        when(thresholdRepository.findAll()).thenReturn(List.of(testThreshold));
        when(alertRepository.save(any(Alert.class)))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> alertService.createAlert(testAlertRequest));
    }

    // ==================== getAllAlerts() Tests ====================

    @Test
    void getAllAlerts_WhenAlertsExist_ReturnsPageOfAlertResponses() {
        Alert alert2 = Alert.builder()
                .id(2L)
                .cityName("Paris")
                .precipitation(80.0)
                .threshold(50.0)
                .timestamp(LocalDateTime.now())
                .severity(Severity.WARNING)
                .status("active")
                .build();

        Page<Alert> alertPage = new PageImpl<>(List.of(testAlert, alert2));
        Pageable pageable = PageRequest.of(0, 10);

        when(alertRepository.findAll(pageable)).thenReturn(alertPage);

        Page<AlertResponse> response = alertService.getAllAlerts(pageable);

        assertNotNull(response);
        assertEquals(2, response.getTotalElements());
        assertEquals("London", response.getContent().get(0).getCityName());
        assertEquals("Paris", response.getContent().get(1).getCityName());
        verify(alertRepository, times(1)).findAll(pageable);
    }

    @Test
    void getAllAlerts_WhenNoAlertsExist_ReturnsEmptyPage() {
        Page<Alert> emptyPage = new PageImpl<>(Collections.emptyList());
        Pageable pageable = PageRequest.of(0, 10);

        when(alertRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<AlertResponse> response = alertService.getAllAlerts(pageable);

        assertNotNull(response);
        assertEquals(0, response.getTotalElements());
        assertTrue(response.getContent().isEmpty());
        verify(alertRepository, times(1)).findAll(pageable);
    }

    @Test
    void getAllAlerts_WhenPageableIsNull_ThrowsException() {
        assertThrows(Exception.class, () -> alertService.getAllAlerts(null));
    }
}
