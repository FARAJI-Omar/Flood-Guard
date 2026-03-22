package com.floodguard.dto.response;

import com.floodguard.enums.Severity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AlertResponse {
    private Long id;
    private String cityName;
    private Double precipitation;
    private Double threshold;
    private LocalDateTime timestamp;
    private Severity severity;
    private String status;
}
