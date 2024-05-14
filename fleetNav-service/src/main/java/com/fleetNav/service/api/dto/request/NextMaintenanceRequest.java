package com.fleetNav.service.api.dto.request;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceRequest {
    private String date;
    private String hour;
    private String location;
}
