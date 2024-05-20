package com.fleetNav.service.api.dto.request;


import jakarta.validation.constraints.*;
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
    @NotNull(message = "Driver earning is required")
    @DecimalMin(
            value = "0.01",
            message = "Value must be greater than 0"
    )
    private Double driverEarning;
    @NotNull(message = "Driver earning is required")
    @DecimalMin(
            value = "0.01",
            message = "Value must be greater than 0"
    )
    private Double ownerEarning;
    @NotNull(message = "Route ID is required")
    private UUID routeId;
    @NotNull(message = "Vehicle ID is required")
    private UUID vehicleId;
    @Size(
            max = 32,
            message = "Driver id cannot be longer than 32 characters."
    )
    private String driverId;
    @NotBlank(message = "Status must not be null")
    @Pattern(regexp = "ASSIGNED|COMPLETED|CREATED", message = "The state must be ASSIGNED, COMPLETED or CREATED")
    private String status;
}
