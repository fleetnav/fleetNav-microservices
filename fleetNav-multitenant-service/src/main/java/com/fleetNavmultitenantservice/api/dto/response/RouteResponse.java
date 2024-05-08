package com.fleetNavmultitenantservice.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
    private UUID id;
    private String name;
    private String destination;
    private String origin;
    private String averageTime;
    private String mileage;
    private List<StopResponse> stop;
    private CostResponse cost;
}
