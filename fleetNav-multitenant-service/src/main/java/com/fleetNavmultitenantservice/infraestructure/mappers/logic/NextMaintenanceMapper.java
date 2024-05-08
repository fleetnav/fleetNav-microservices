package com.fleetNavmultitenantservice.infraestructure.mappers.logic;


import com.fleetNavmultitenantservice.api.dto.request.NextMaintenanceRequest;
import com.fleetNavmultitenantservice.api.dto.response.NextMaintenanceResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.NextMaintenance;
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
