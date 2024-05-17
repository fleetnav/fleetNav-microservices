package com.fleetNav.service.api.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
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
            message = "Value of service must be greater than 0"
    )
    private Double price;
    @NotNull(message = "Trip id is required")
    private UUID tripId;
}
