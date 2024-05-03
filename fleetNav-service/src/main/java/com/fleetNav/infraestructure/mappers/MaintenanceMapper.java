package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.MaintenanceRequest;
import com.fleetNav.api.dto.response.MaintenanceResponse;
import com.fleetNav.domain.entities.Comment;
import com.fleetNav.domain.entities.Maintenance;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {MaintenanceMapper.class})
public interface MaintenanceMapper {
    default UUID mapMaintenanceToUUID(Maintenance maintenance) {
        return maintenance != null ? maintenance.getId() : null;
    }

    Maintenance toMaintenance(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse toMaintenanceResponse(Maintenance maintenance);
}
