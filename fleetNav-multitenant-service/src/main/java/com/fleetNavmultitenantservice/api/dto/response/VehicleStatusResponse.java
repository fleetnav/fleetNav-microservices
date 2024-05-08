package com.fleetNavmultitenantservice.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatusResponse {
    private UUID id;
    private String observation;
    private String driverId;
}
