package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest {
    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "It is not possible to enter a date later than the current date.")
    private LocalDate date;
    @NotBlank(message = "Observation is required")
    @Size(
            max = 200,
            message = "Observation cannot be longer than 200 characters."
    )
    private String observation;
    @NotNull(message = "Price is required")
    @DecimalMin(
            value = "0.01",
            message = "Value of maintenance must be greater than 0"
    )
    private Double price;
    @NotNull(message = "Vehicle id is required")
    private UUID vehicleId;
}
