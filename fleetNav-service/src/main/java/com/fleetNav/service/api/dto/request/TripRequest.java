package com.fleetNav.service.api.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {
    @NotNull(message = "Date start is required")
    @FutureOrPresent(message = "It is not possible to enter a date later than the current date.")
    private LocalDate dateStart;
    @NotNull(message = "Date end is required")
    @FutureOrPresent(message = "It is not possible to enter a date later than the current date.")
    private LocalDate dateEnd;
    @NotNull(message = "Cost is required")
    @DecimalMin(
            value = "0.01",
            message = "Value must be greater than 0"
    )
    private Double cost;
    @NotNull(message = "Route ID is required")
    private UUID routeId;
    @NotNull(message = "Vehicle ID is required")
    private UUID vehicleId;
    @NotNull(message = "Driver ID is required")
    private UUID driverId;
}
