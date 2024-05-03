package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Vehicle;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceResponse {
    private String date;
    private String hour;
    private String location;
    private Vehicle vehicle;
}
