package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.VehicleRequest;
import com.fleetNav.api.dto.response.VehicleResponse;
import com.fleetNav.domain.entities.Maintenance;
import com.fleetNav.domain.entities.Trip;
import com.fleetNav.domain.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {TripMapper.class, MaintenanceMapper.class})
public interface VehicleMapper {

    Vehicle toVehicle(VehicleRequest vehicleRequest);

    @Mappings({
            @Mapping(source = "trips", target = "trip"),
            @Mapping(source = "maintenances", target = "maintenance")
    })
    VehicleResponse toVehicleResponse(Vehicle vehicle);

    // Método de mapeo para List<Trip> a Trip
    default Trip mapTripsToTrip(List<Trip> trips) {
        return trips != null && !trips.isEmpty() ? trips.get(0) : null;
    }

    // Método de mapeo para List<Maintenance> a Maintenance
    default Maintenance mapMaintenancesToMaintenance(List<Maintenance> maintenances) {
        return maintenances != null && !maintenances.isEmpty() ? maintenances.get(0) : null;
    }
}
