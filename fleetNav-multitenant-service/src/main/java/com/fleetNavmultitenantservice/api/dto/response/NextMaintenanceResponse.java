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
public class NextMaintenanceResponse {
    private UUID id;
    private String date;
    private String hour;
    private String location;
}
