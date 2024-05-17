package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteUpdateRequest {
    @NotBlank(message = "Name is required")
    @Size(
            max = 45,
            message = "Name cannot be longer than 45 characters."
    )
    private String name;
    @NotBlank(message = "Destination is required")
    @Size(
            max = 45,
            message = "Destination cannot be longer than 45 characters."
    )
    private String destination;
    @NotBlank(message = "Origin is required")
    @Size(
            max = 45,
            message = "Origin cannot be longer than 45 characters."
    )
    private String origin;
    @NotBlank(message = "Average time is required")
    @Size(
            max = 5,
            message = "Average time cannot be longer than 5 characters."
    )
    private String averageTime;
    @NotBlank(message = "Mileage is required")
    @Size(
            max = 10,
            message = "Mileage cannot be longer than 10 characters."
    )
    private String mileage;
}
