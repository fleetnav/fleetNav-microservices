package com.fleetNav.api.dto.request;

import com.fleetNav.api.dto.response.VehicleResponse;
import com.fleetNav.domain.entities.Vehicle;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest {
    private String date;
    private String observation;
    private Double price;
    private VehicleResponse vehicle;
}
