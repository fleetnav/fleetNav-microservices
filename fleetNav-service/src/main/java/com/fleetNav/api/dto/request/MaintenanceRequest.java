package com.fleetNav.api.dto.request;



import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest {
    private String date;
    private String observation;
    private Double price;
    private UUID vehicleId;
}
