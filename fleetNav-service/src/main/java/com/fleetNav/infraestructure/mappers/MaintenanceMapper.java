package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.MaintenanceRequest;
import com.fleetNav.api.dto.response.MaintenanceResponse;
import com.fleetNav.domain.entities.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {MaintenanceMapper.class})
public interface MaintenanceMapper {
    @Mapping(target = "id", ignore = true)
    Maintenance toMaintenance(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse toMaintenanceResponse(Maintenance maintenance);
}
