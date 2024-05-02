package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostResponse {
  private Integer numberToll;
  private Double priceToll;
  private Double priceGasoline;
  private Double totalPrice;
  private Route route;
}
