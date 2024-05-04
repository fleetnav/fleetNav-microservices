package com.fleetNav.api.dto.request;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
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
