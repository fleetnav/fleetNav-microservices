package com.fleetNav.api.dto.request;

import com.fleetNav.domain.entities.Maintenance;
import com.fleetNav.domain.entities.NextMaintenance;
import com.fleetNav.domain.entities.Trip;
import com.fleetNav.domain.entities.VehicleStatus;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private String numberPlate;
    private String model;
    private String mileage;
    private String status;
    private NextMaintenance nextMaintenance;
    private VehicleStatus vehicleStatus;
    private Trip trip;
    private Maintenance maintenance;
    private String ownerId;
}
