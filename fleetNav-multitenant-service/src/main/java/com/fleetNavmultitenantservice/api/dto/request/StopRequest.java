package com.fleetNavmultitenantservice.api.dto.request;

import com.fleetNavmultitenantservice.domain.entities.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopRequest {
   private String name;
  private String location;
  private String time;
  private Route route;
}
