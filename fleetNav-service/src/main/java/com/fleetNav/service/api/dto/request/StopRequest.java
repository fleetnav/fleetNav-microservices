package com.fleetNav.service.api.dto.request;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
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
