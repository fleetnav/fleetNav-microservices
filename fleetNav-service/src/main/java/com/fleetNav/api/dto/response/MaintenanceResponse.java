package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponse {
  private String date;
  private String observation;
  private Double price;
  private Vehicle vehicle;
}
