package com.fleetNav.service.api.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceRequest {
    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "It is not possible to enter a date earlier than the current date.")
    private LocalDate date;
    @NotBlank(message = "Hour is required")
    @Size(
            max = 10,
            message = "Hour cannot be longer than 10 characters."
    )
    private String hour;
    @NotBlank(message = "Hour is required")
    @Size(
            max = 45,
            message = "Location cannot be longer than 45 characters."
    )
    private String location;
}
