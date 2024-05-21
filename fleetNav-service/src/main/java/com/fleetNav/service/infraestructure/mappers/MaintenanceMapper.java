package com.fleetNav.service.infraestructure.mappers;

import com.fleetNav.service.api.dto.request.MaintenanceRequest;
import com.fleetNav.service.api.dto.response.MaintenanceResponse;
import com.fleetNav.service.domain.entities.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {MaintenanceMapper.class})
public interface MaintenanceMapper {
    @Mapping(target = "id", ignore = true)
    Maintenance toMaintenance(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse toMaintenanceResponse(Maintenance maintenance);

    void updateFromMaintenanceRequest(MaintenanceRequest maintenanceRequest, @MappingTarget Maintenance maintenance);

}
