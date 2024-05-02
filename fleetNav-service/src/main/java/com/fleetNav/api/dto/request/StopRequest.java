package com.fleetNav.api.dto.request;

import com.fleetNav.domain.entities.Route;

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