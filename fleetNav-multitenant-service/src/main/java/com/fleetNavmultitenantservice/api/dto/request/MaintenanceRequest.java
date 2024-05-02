package com.fleetNavmultitenantservice.api.dto.request;

import com.fleetNavmultitenantservice.domain.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest {
  private String date;
  private String observation;
  private Double price;
  private Vehicle vehicle;
}
