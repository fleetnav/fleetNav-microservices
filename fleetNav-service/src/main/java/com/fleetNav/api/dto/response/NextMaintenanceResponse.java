package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Vehicle;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceResponse {
    private UUID id;
    private String date;
    private String hour;
    private String location;
}
