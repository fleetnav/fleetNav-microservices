package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Maintenance;
import com.fleetNav.domain.entities.NextMaintenance;
import com.fleetNav.domain.entities.Trip;
import com.fleetNav.domain.entities.VehicleStatus;


import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private UUID id;
    private String mileage;
    private String model;
    private String numberPlate;
    private UUID ownerId;
    private String status;
    private NextMaintenance nextMaintenance;
    private VehicleStatus vehicleStatus;
}
