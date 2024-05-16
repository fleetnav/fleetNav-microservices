package com.fleetNav.service.api.dto.request;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {
    private String dateStart;
    private String dateEnd;
    private Double cost;
    private UUID routeId;
    private UUID vehicleId;
    private UUID driverId;
}