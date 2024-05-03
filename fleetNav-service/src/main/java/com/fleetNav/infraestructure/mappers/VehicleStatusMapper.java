package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.VehicleStatusRequest;
import com.fleetNav.api.dto.response.VehicleStatusResponse;
import com.fleetNav.domain.entities.VehicleStatus;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface VehicleStatusMapper {
    default UUID mapVehicleSatusToUUID(VehicleStatus vehicleStatus) {
        return vehicleStatus != null ? vehicleStatus.getId() : null;
    }

    VehicleStatus toVehicleStatus(VehicleStatusRequest vehicleStatusRequest);

    VehicleStatusResponse toVehicleStatusResponse(VehicleStatus vehicleStatus);

}
