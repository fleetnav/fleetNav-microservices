package com.fleetNavmultitenantservice.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
    private String mileage;
    private String model;
    private String numberPlate;
    private UUID ownerId;
    private String status;
    private NextMaintenanceRequest nextMaintenance;
    private VehicleStatusRequest vehicleStatus;
}
