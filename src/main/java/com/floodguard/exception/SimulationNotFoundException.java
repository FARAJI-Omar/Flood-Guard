package com.floodguard.exception;

public class SimulationNotFoundException extends RuntimeException {

    public SimulationNotFoundException(Long id) {
        super("Simulation not found with id: " + id);
    }
}
