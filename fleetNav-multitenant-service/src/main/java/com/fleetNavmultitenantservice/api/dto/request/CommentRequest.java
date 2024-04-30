package com.fleetNavmultitenantservice.api.dto.request;

import com.fleetNavmultitenantservice.domain.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
  private String date;
  private String observation;
  private Double price;
  private Trip trip;
}
