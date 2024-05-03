package com.fleetNav.api.dto.request;

import com.fleetNav.domain.entities.Vehicle;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatusRequest {
    private String observation;
    private String driverId;
}
