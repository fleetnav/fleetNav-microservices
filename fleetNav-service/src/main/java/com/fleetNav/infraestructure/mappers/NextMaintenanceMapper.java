package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.api.dto.response.NextMaintenanceResponse;
import com.fleetNav.domain.entities.NextMaintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {NextMaintenanceMapper.class})
public interface NextMaintenanceMapper {
    default UUID mapNextMaintenanceToUUID(NextMaintenance nextMaintenance) {
        return nextMaintenance != null ? nextMaintenance.getId() : null;
    }

    @Mapping(target = "id", ignore = true)
    NextMaintenance toNextMaintenance(NextMaintenanceRequest nextMaintenanceRequest);

    NextMaintenanceResponse toNextMaintenanceResponse(NextMaintenance nextMaintenance);

}
