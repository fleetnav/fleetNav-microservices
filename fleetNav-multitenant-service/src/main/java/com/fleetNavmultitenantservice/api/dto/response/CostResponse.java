package com.fleetNavmultitenantservice.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostResponse {
    private UUID id;
    private Integer numberToll;
    private Double priceToll;
    private Double priceGasoline;
    private Double totalPrice;
}
