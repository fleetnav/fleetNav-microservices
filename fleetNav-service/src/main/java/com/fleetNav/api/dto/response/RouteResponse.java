package com.fleetNav.api.dto.response;

import java.util.List;

import com.fleetNav.domain.entities.Cost;
import com.fleetNav.domain.entities.Stop;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
    private String name;
    private String destination;
    private String origin;
    private String averageTime;
    private String mileage;
    private List<Stop> stop;
    private Cost cost;
}
