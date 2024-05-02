package com.fleetNavmultitenantservice.api.dto.request;

import java.util.List;

import com.fleetNavmultitenantservice.domain.entities.Comment;
import com.fleetNavmultitenantservice.domain.entities.Route;
import com.fleetNavmultitenantservice.domain.entities.Vehicle;

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
