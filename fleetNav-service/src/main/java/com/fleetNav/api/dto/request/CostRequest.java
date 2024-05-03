package com.fleetNav.api.dto.request;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostRequest {
    private Integer numberToll;
    private Double priceToll;
    private Double priceGasoline;
    private Double totalPrice;
}
