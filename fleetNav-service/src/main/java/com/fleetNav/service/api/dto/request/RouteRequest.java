package com.fleetNav.service.api.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RouteRequest {
    private String name;
    private String destination;
    private String origin;
    private String averageTime;
    private String mileage;
    private CostRequest cost;
}
