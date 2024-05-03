package com.fleetNav.api.dto.request;


import com.fleetNav.domain.entities.Route;
import com.fleetNav.domain.entities.Vehicle;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {
    private String dateStart;
    private String dateEnd;
    private Double cost;
    private Route route;
    private Vehicle vehicle;
    private String driver_id;
}
