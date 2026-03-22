package com.floodguard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AlertRequest {
    @NotBlank
    private String cityName;

    @NotNull
    @Positive
    private Double precipitation;
}
