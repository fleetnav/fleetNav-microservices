package com.fleetNavmultitenantservice.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
