package com.fleetNavmultitenantservice.infraestructure.mappers.logic;


import com.fleetNavmultitenantservice.api.dto.request.VehicleStatusRequest;
import com.fleetNavmultitenantservice.api.dto.response.VehicleStatusResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.VehicleStatus;
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
