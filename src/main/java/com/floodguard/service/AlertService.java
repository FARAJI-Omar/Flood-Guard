package com.floodguard.service;

import com.floodguard.dto.request.AlertRequest;
import com.floodguard.dto.request.ThresholdRequest;
import com.floodguard.dto.response.AlertResponse;
import com.floodguard.dto.response.ThresholdResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {
    ThresholdResponse getThreshold();

    ThresholdResponse updateThreshold(ThresholdRequest request);

    AlertResponse createAlert(AlertRequest request);
    
    Page<AlertResponse> getAllAlerts(Pageable pageable);
}
