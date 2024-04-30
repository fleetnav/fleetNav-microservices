package com.fleetNavmultitenantservice.api.dto.request;

import com.fleetNavmultitenantservice.domain.entities.Maintenance;
import com.fleetNavmultitenantservice.domain.entities.NextMaintenance;
import com.fleetNavmultitenantservice.domain.entities.Trip;
import com.fleetNavmultitenantservice.domain.entities.VehicleStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
