package com.fleetNavmultitenantservice.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
