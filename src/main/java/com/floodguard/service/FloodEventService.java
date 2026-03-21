package com.floodguard.service;

import com.floodguard.dto.request.FloodEventRequest;
import com.floodguard.dto.request.FloodEventUpdateRequest;
import com.floodguard.dto.response.FloodEventCollectionResponse;
import com.floodguard.dto.response.FloodEventFeature;
import com.floodguard.enums.Severity;

public interface FloodEventService {

    FloodEventCollectionResponse getAll(Integer year, Severity severity);

    FloodEventFeature getById(Long id);

    FloodEventFeature create(FloodEventRequest request);

    FloodEventFeature update(Long id, FloodEventUpdateRequest request);

    void delete(Long id);
}
