package com.fleetNav.infraestructure.services;

import com.fleetNav.api.dto.request.VehicleRequest;
import com.fleetNav.api.dto.response.NextMaintenanceResponse;
import com.fleetNav.api.dto.response.VehicleResponse;
import com.fleetNav.api.dto.response.VehicleStatusResponse;
import com.fleetNav.domain.entities.NextMaintenance;
import com.fleetNav.domain.entities.Vehicle;
import com.fleetNav.domain.entities.VehicleStatus;
import com.fleetNav.domain.repositories.NextMaintenanceRepository;
import com.fleetNav.domain.repositories.VehicleRepository;
import com.fleetNav.domain.repositories.VehicleStatusRepository;
import com.fleetNav.infraestructure.abstract_services.IVehicleService;
import com.fleetNav.infraestructure.mappers.VehicleMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleService implements IVehicleService {
    @Autowired
    private final VehicleRepository vehicleRepository;
    @Autowired
    private final NextMaintenanceRepository nextMaintenanceRepository;
    @Autowired
    private final VehicleStatusRepository vehicleStatusRepository;
    @Autowired
    private final VehicleStatusService vehicleStatusService;
    @Autowired
    private final NextMaintenanceService nextMaintenanceService;
    @Autowired
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse create(VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleRequest);
        NextMaintenanceResponse nextMaintenanceResponse = nextMaintenanceService.create(vehicleRequest.getNextMaintenance());
        VehicleStatusResponse vehicleStatusResponse = vehicleStatusService.create(vehicleRequest.getVehicleStatus());

        NextMaintenance nextMaintenance = nextMaintenanceRepository.findById(nextMaintenanceResponse.getId()).orElseThrow();
        VehicleStatus vehicleStatus = vehicleStatusRepository.findById(vehicleStatusResponse.getId()).orElseThrow();

        vehicle.setVehicleStatus(vehicleStatus);
        vehicle.setNextMaintenance(nextMaintenance);

        Vehicle saveVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleResponse(saveVehicle);
    }

    @Override
    public VehicleResponse update(UUID id, VehicleRequest vehicleRequest) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with the id" + id));
        vehicleMapper.updateFromVehicleRequest(vehicleRequest, existingVehicle);
        Vehicle updateVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toVehicleResponse(updateVehicle);
    }

    @Override
    public void delete(UUID uuid) {
        vehicleRepository.deleteById(uuid);
    }

    @Override
    public Page<VehicleResponse> getAll(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        return vehiclePage.map(vehicleMapper::toVehicleResponse);
    }

    @Override
    public Optional<VehicleResponse> getById(UUID uuid) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(uuid);
        return vehicle.map(vehicleMapper::toVehicleResponse);
    }
}
