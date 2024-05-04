package com.fleetNav.api.dto.response;

import com.fleetNav.domain.entities.Route;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopResponse {
    private UUID id;
    private String name;
    private String location;
    private String time;
}
