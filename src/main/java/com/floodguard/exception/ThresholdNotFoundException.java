package com.floodguard.exception;

public class ThresholdNotFoundException extends RuntimeException {
    public ThresholdNotFoundException(String message) {
        super(message);
    }
}
