package com.floodguard.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ThresholdRequest {
    @NotNull
    @Positive
    private Double value;
}
