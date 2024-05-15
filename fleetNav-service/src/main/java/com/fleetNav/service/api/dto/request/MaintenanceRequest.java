package com.fleetNav.service.api.dto.request;



import lombok.*;

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
