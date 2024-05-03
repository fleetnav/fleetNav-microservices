package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.MaintenanceRequest;
import com.fleetNav.api.dto.request.VehicleStatusRequest;
import com.fleetNav.api.dto.response.MaintenanceResponse;
import com.fleetNav.domain.entities.Maintenance;
import com.fleetNav.domain.entities.VehicleStatus;
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

    @Mapping(target = "vehicle",source = "vehicle")
    MaintenanceResponse toMaintenanceResponse(Maintenance maintenance);

    void updateFromMaintenanceRequest(MaintenanceRequest maintenanceRequest, @MappingTarget Maintenance maintenance);

}
