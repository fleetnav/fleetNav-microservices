package com.fleetNavmultitenantservice.infraestructure.mappers.logic;


import com.fleetNavmultitenantservice.api.dto.request.VehicleRequest;
import com.fleetNavmultitenantservice.api.dto.response.VehicleResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {NextMaintenanceMapper.class, VehicleStatusMapper.class})
public interface VehicleMapper {

    @Mapping(target = "id",ignore = true)
    Vehicle toVehicle(VehicleRequest vehicleRequest);

    VehicleResponse toVehicleResponse(Vehicle vehicle);

    void updateFromVehicleRequest(VehicleRequest vehicleRequest, @MappingTarget Vehicle vehicle);

}
