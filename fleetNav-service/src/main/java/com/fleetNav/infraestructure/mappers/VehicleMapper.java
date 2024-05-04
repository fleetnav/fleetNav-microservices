package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.VehicleRequest;
import com.fleetNav.api.dto.response.VehicleResponse;
import com.fleetNav.domain.entities.Vehicle;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {NextMaintenanceMapper.class, VehicleStatusMapper.class})
public interface VehicleMapper {

    @Mapping(target = "id",ignore = true)
    Vehicle toVehicle(VehicleRequest vehicleRequest);

    VehicleResponse toVehicleResponse(Vehicle vehicle);

    void updateFromVehicleRequest(VehicleRequest vehicleRequest, @MappingTarget Vehicle vehicle);

}
