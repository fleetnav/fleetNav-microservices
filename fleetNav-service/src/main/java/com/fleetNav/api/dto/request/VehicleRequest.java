package com.fleetNav.api.dto.request;

import com.fleetNav.domain.entities.Maintenance;
import com.fleetNav.domain.entities.NextMaintenance;
import com.fleetNav.domain.entities.Trip;
import com.fleetNav.domain.entities.VehicleStatus;

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
