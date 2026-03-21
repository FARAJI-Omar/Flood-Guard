package com.floodguard.exception;

public class FloodEventNotFoundException extends RuntimeException {
    public FloodEventNotFoundException(Long id) {
        super("Flood event not found with id: " + id);
    }
}
