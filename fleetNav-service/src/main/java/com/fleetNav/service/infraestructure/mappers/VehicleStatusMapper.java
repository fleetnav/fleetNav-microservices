package com.fleetNav.service.infraestructure.mappers;

import com.fleetNav.service.api.dto.request.VehicleStatusRequest;
import com.fleetNav.service.api.dto.response.VehicleStatusResponse;
import com.fleetNav.service.domain.entities.VehicleStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleStatusMapper {

    @Mapping(target = "id", ignore = true)
    VehicleStatus toVehicleStatus(VehicleStatusRequest vehicleStatusRequest);

    VehicleStatusResponse toVehicleStatusResponse(VehicleStatus vehicleStatus);

    void updateFromVehicleStatusRequest(VehicleStatusRequest vehicleStatusRequest, @MappingTarget VehicleStatus vehicleStatus);

}
