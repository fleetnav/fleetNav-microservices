package com.fleetNavmultitenantservice.api.dto.request;

import com.fleetNavmultitenantservice.domain.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceRequest {
  private String date;
  private String hour;
  private String location;
   private Vehicle vehicle;
}
