package com.fleetNavmultitenantservice.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopRequest {
    private String name;
    private String location;
    private String time;
    private UUID routeId;
}
