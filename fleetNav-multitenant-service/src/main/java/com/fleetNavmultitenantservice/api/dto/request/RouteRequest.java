package com.fleetNavmultitenantservice.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {
    private String name;
    private String destination;
    private String origin;
    private String averageTime;
    private String mileage;
    private CostRequest cost;
}
