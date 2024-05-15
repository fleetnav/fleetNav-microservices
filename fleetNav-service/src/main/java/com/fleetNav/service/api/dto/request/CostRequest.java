package com.fleetNav.service.api.dto.request;

import lombok.*;

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
