package com.fleetNavmultitenantservice.infraestructure.mappers.logic;

import com.fleetNavmultitenantservice.api.dto.request.MaintenanceRequest;
import com.fleetNavmultitenantservice.api.dto.response.MaintenanceResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface MaintenanceMapper {
    @Mapping(target = "id", ignore = true)
    Maintenance toMaintenance(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse toMaintenanceResponse(Maintenance maintenance);

    void updateFromMaintenanceRequest(MaintenanceRequest maintenanceRequest, @MappingTarget Maintenance maintenance);

}
