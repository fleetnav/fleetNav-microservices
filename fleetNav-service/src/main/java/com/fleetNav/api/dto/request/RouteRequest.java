package com.fleetNav.api.dto.request;

import java.util.List;

import com.fleetNav.domain.entities.Cost;
import com.fleetNav.domain.entities.Stop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {
  private String name;
  private String destination;
  private String origin;
  private String averageTime;
  private String mileage;
  private List<Stop> stop;
  private Cost cost;
}