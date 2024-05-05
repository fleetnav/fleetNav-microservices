package com.fleetNav.api.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

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
