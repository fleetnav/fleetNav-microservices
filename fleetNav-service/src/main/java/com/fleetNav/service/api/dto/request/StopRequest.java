package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopRequest {
    @NotBlank(message = "Name is required")
    @Size(
            max = 45,
            message = "Name cannot be longer than 45 characters."
    )
    private String name;
    @NotBlank(message = "Location is required")
    @Size(
            max = 45,
            message = "Location cannot be longer than 45 characters."
    )
    private String location;
    @NotBlank(message = "Time is required")
    @Size(
            max = 45,
            message = "Time cannot be longer than 45 characters."
    )
    private String time;
    @NotNull(message = "Route ID is required")
    private UUID routeId;
}
