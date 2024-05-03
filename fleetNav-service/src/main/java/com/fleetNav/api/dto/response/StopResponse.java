package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Route;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopResponse {
    private String name;
    private String location;
    private String time;
    private Route route;
}
