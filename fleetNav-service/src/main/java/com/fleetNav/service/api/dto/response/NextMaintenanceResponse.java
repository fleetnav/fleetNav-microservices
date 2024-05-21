package com.fleetNav.service.api.dto.response;



import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NextMaintenanceResponse {
    private UUID id;
    private LocalDate date;
    private String hour;
    private String location;
}
