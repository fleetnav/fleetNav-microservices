package com.fleetNav.api.dto.request;

import java.util.List;

import com.fleetNav.domain.entities.Comment;
import com.fleetNav.domain.entities.Route;
import com.fleetNav.domain.entities.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {
  private String dateStart;
  private String dateEnd;
  private Double cost;
  private List<Comment> comments;
  private Route route;
  private Vehicle vehicle;
  private String driver_id;
}
