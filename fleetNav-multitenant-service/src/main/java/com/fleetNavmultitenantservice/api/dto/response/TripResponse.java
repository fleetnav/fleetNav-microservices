package com.fleetNavmultitenantservice.api.dto.response;

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
public class TripResponse {
  private String dateStart;
  private String dateEnd;
  private Double cost;
  private List<Comment> comment;
  private Route route;
  private Vehicle vehicle;
}
