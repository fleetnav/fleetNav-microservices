package com.fleetNav.api.dto.response;

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
