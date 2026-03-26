package com.floodguard.dto.response;

import com.floodguard.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloodEventProperties {

    private Long id;
    private String name;
    private LocalDate eventDate;
    private Severity severity;
    private Double areaKm2;
    private String source;
}
