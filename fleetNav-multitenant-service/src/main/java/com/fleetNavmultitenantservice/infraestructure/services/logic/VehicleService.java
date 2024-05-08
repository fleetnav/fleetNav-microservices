package com.fleetNavmultitenantservice.infraestructure.services.logic;


import com.fleetNavmultitenantservice.api.dto.request.VehicleRequest;
import com.fleetNavmultitenantservice.api.dto.response.NextMaintenanceResponse;
import com.fleetNavmultitenantservice.api.dto.response.VehicleResponse;
import com.fleetNavmultitenantservice.api.dto.response.VehicleStatusResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.NextMaintenance;
import com.fleetNavmultitenantservice.domain.entities.logic.Vehicle;
import com.fleetNavmultitenantservice.domain.entities.logic.VehicleStatus;
import com.fleetNavmultitenantservice.domain.repositories.logic.NextMaintenanceRepository;
import com.fleetNavmultitenantservice.domain.repositories.logic.VehicleRepository;
import com.fleetNavmultitenantservice.domain.repositories.logic.VehicleStatusRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.IVehicleService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.VehicleMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService implements IVehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private NextMaintenanceRepository nextMaintenanceRepository;
    @Autowired
    private VehicleStatusRepository vehicleStatusRepository;
    @Autowired
    private VehicleStatusService vehicleStatusService;
    @Autowired
    private NextMaintenanceService nextMaintenanceService;
    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse create(VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehicleRequest);
        NextMaintenanceResponse nextMaintenanceResponse = nextMaintenanceService.create(vehicleRequest.getNextMaintenance());
        VehicleStatusResponse vehicleStatusResponse = vehicleStatusService.create(vehicleRequest.getVehicleStatus());

        NextMaintenance nextMaintenance = nextMaintenanceRepository.findById(nextMaintenanceResponse.getId())
                .orElseThrow(() -> new IdNotFoundException("NEXT_MAINTENANCE", nextMaintenanceResponse.getId()));
        VehicleStatus vehicleStatus = vehicleStatusRepository.findById(vehicleStatusResponse.getId())
                .orElseThrow(() -> new IdNotFoundException("VEHICLE", vehicleStatusResponse.getId()));

        vehicle.setVehicleStatus(vehicleStatus);
        vehicle.setNextMaintenance(nextMaintenance);

        Vehicle saveVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleResponse(saveVehicle);
    }

    @Override
    public VehicleResponse update(UUID id, VehicleRequest vehicleRequest) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("VEHICLE", id));

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
        if (vehicle.isEmpty()) throw new IdNotFoundException("VEHICLE", uuid);
        return vehicle.map(vehicleMapper::toVehicleResponse);
    }
}
