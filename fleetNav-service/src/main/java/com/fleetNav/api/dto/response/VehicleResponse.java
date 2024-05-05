package com.fleetNav.api.dto.response;




import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private NextMaintenanceResponse nextMaintenance;
    private VehicleStatusResponse vehicleStatus;
    private List<MaintenanceResponse> maintenances;
}
