package com.fleetNavmultitenantservice.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {
    private UUID id;
    private UUID driverId;
    private String dateStart;
    private String dateEnd;
    private Double cost;
    private List<CommentResponse> comments;
    private RouteResponse route;
    private VehicleResponse vehicle;
}
