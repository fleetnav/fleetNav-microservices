package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.VehicleRequest;
import com.fleetNav.api.dto.response.VehicleResponse;
import com.fleetNav.domain.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface VehicleMapper {
    default UUID mapVehicleToUUID(Vehicle vehicle) {
        return vehicle != null ? vehicle.getId() : null;
    }

    Vehicle toVehicle(VehicleRequest vehicleRequest);

    @Mappings({
            @Mapping(source = "trips", target = "trip"),
            @Mapping(source = "maintenances", target = "maintenance")
    })
    VehicleResponse toVehicleResponse(Vehicle vehicle);

}
