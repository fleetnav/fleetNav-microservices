package com.fleetNav.service.infraestructure.mappers;

import com.fleetNav.service.api.dto.request.MaintenanceRequest;
import com.fleetNav.service.api.dto.response.MaintenanceResponse;
import com.fleetNav.service.domain.entities.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface MaintenanceMapper {
    default UUID mapTripToUUID(Maintenance maintenance) {
        return maintenance != null ? maintenance.getId() : null;
    }
    @Mapping(target = "id", ignore = true)
    Maintenance toMaintenance(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse toMaintenanceResponse(Maintenance maintenance);

    void updateFromMaintenanceRequest(MaintenanceRequest maintenanceRequest, @MappingTarget Maintenance maintenance);

}
