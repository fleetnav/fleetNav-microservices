package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleUpdateRequest {
    @NotBlank(message = "mileage must not be null")
    @Size(
            max = 10,
            message = "Mileage cannot be longer than 10 characters."
    )
    private String mileage;
    @NotBlank(message = "model must not be null")
    @Size(
            min = 4,
            max = 4,
            message = "Model cannot be longer than 4 characters."
    )
    private String model;
    @NotBlank(message = "Number plate must not be null")
    @Size(
            min = 6,
            max = 6,
            message = "Number plate cannot be longer than 6 characters."
    )
    private String numberPlate;
    @NotBlank(message = "Status must not be null")
    @Pattern(regexp = "ON_TRIP|OFF_TRIP|ON_MAINTENANCE", message = "The state must be ON_TRIP, OFF_TRIP or ON_MAINTENANCE")
    private String status;
}
