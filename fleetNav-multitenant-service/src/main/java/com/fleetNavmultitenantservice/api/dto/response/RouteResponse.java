package com.fleetNavmultitenantservice.api.dto.response;

import java.util.List;

import com.fleetNavmultitenantservice.domain.entities.Cost;
import com.fleetNavmultitenantservice.domain.entities.Stop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {
  private String name;
  private String destination;
  private String origin;
  private String averageTime;
  private String mileage;
  private List<Stop> stop;
  private Cost cost;
}
