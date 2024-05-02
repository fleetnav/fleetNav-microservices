package com.fleetNavmultitenantservice.api.dto.response;

import com.fleetNavmultitenantservice.domain.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class CommentResponse {
  private String date;
  private String observation;
  private Double price;
   private Trip trip;
}
