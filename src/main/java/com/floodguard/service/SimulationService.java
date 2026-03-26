package com.floodguard.service;

import com.floodguard.dto.request.SimulationRequest;
import com.floodguard.dto.response.SimulationResponse;

import java.util.List;

public interface SimulationService {

    SimulationResponse create(SimulationRequest request);

    List<SimulationResponse> getMine();

    List<SimulationResponse> getHistory(Long userId);

    SimulationResponse getById(Long id);
}
