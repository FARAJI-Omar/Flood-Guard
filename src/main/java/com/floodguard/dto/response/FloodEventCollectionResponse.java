package com.floodguard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloodEventCollectionResponse {

    private final String type = "FeatureCollection";
    private List<FloodEventFeature> features;
    private int totalCount;
}
