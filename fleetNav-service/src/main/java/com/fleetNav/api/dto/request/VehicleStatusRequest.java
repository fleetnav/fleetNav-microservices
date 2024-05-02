package com.fleetNav.api.dto.request;

import com.fleetNav.domain.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatusRequest {
   private String observation;
  private String driverId;
  private Vehicle vehicle;
}
