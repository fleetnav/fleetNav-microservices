package com.fleetNavmultitenantservice.api.dto.response;

import com.fleetNavmultitenantservice.domain.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceResponse {
   private String date;
  private String hour;
  private String location;
   private Vehicle vehicle;
}
