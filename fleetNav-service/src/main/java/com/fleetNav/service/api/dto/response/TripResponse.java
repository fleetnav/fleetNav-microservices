package com.fleetNav.service.api.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {
    private UUID id;
    private String driverId;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Double driverEarning;
    private Double ownerEarning;
    private String status;
    private List<CommentResponse> comments;
    private RouteResponse route;
    private VehicleResponse vehicle;
}
