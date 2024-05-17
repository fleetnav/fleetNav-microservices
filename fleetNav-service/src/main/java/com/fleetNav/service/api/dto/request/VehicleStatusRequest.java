package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatusRequest {
    @NotBlank(message = "Observation is required")
    @Size(
            max = 200,
            message = "Observation cannot be longer than 200 characters."
    )
    private String observation;
    @NotNull(message = "Diver id is required")
    private UUID driverId;
}
