package com.fleetNavmultitenantservice.infraestructure.services.logic;

import com.fleetNavmultitenantservice.api.dto.request.MaintenanceRequest;
import com.fleetNavmultitenantservice.api.dto.response.MaintenanceResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Maintenance;
import com.fleetNavmultitenantservice.domain.entities.logic.Vehicle;
import com.fleetNavmultitenantservice.domain.repositories.logic.MaintenanceRepository;
import com.fleetNavmultitenantservice.domain.repositories.logic.VehicleRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.IMaintenanceService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.MaintenanceMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceService implements IMaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceResponse create(MaintenanceRequest maintenanceRequest) {
        Vehicle vehicle = vehicleRepository.findById(maintenanceRequest.getVehicleId())
                .orElseThrow(() -> new IdNotFoundException("VEHICLE", maintenanceRequest.getVehicleId()));

        Maintenance maintenance = maintenanceMapper.toMaintenance(maintenanceRequest);
        maintenance.setVehicle(vehicle);
        Maintenance saveMaintenance = maintenanceRepository.save(maintenance);
        return maintenanceMapper.toMaintenanceResponse(saveMaintenance);
    }

    @Override
    public MaintenanceResponse update(UUID id, MaintenanceRequest maintenanceRequest) {
        Maintenance existingMaintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("MAINTENANCE", id));

        maintenanceMapper.updateFromMaintenanceRequest(maintenanceRequest, existingMaintenance);
        Maintenance updateMaintenance = maintenanceRepository.save(existingMaintenance);
        return maintenanceMapper.toMaintenanceResponse(updateMaintenance);
    }

    @Override
    public void delete(UUID uuid) {
        maintenanceRepository.deleteById(uuid);
    }

    @Override
    public Page<MaintenanceResponse> getAll(Pageable pageable) {
        Page<Maintenance> maintenancePage = maintenanceRepository.findAll(pageable);
        return maintenancePage.map(maintenanceMapper::toMaintenanceResponse);
    }

    @Override
    public Optional<MaintenanceResponse> getById(UUID uuid) {
        Optional<Maintenance> maintenance = maintenanceRepository.findById(uuid);
        if (maintenance.isEmpty()) throw new IdNotFoundException("MAINTENANCE", uuid);
        return maintenance.map(maintenanceMapper::toMaintenanceResponse);
    }
}
