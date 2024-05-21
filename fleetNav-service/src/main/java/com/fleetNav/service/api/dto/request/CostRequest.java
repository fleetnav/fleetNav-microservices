package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostRequest {
    @NotNull(message = "Number of tolls is required")
    @DecimalMin(
            value = "0",
            message = "Value of service must no be less than 0"
    )
    private Integer numberToll;
    @NotNull(message = "Tolls price is required")
    @DecimalMin(
            value = "0",
            message = "Value of tolls must no be less than 0"
    )
    private Double priceToll;
    @NotNull(message = "Gasoline price is required")
    @DecimalMin(
            value = "0.01",
            message = "Value of gasoline must be greater than 0"
    )
    private Double priceGasoline;
    @NotNull(message = "Total price is required")
    @DecimalMin(
            value = "0.01",
            message = "Value of total price must be greater than 0"
    )
    private Double totalPrice;
}
