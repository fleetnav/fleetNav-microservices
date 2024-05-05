package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.api.dto.response.NextMaintenanceResponse;
import com.fleetNav.domain.entities.NextMaintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface NextMaintenanceMapper {

    @Mapping(target = "id", ignore = true)
    NextMaintenance toNextMaintenance(NextMaintenanceRequest nextMaintenanceRequest);

    NextMaintenanceResponse toNextMaintenanceResponse(NextMaintenance nextMaintenance);

    void updateFromNextMaintenanceRequest(NextMaintenanceRequest nextMaintenanceRequest, @MappingTarget NextMaintenance nextMaintenance);

}
