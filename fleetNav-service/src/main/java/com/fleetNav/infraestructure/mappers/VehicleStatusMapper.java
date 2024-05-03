package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.CostRequest;
import com.fleetNav.api.dto.request.VehicleStatusRequest;
import com.fleetNav.api.dto.response.VehicleStatusResponse;
import com.fleetNav.domain.entities.Cost;
import com.fleetNav.domain.entities.VehicleStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface VehicleStatusMapper {

    @Mapping(target = "id", ignore = true)
    VehicleStatus toVehicleStatus(VehicleStatusRequest vehicleStatusRequest);

    VehicleStatusResponse toVehicleStatusResponse(VehicleStatus vehicleStatus);

    void updateFromVehicleStatusRequest(VehicleStatusRequest vehicleStatusRequest, @MappingTarget VehicleStatus vehicleStatus);

}
