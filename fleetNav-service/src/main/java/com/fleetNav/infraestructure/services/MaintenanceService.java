package com.fleetNav.infraestructure.services;

import com.fleetNav.api.dto.request.MaintenanceRequest;
import com.fleetNav.api.dto.response.MaintenanceResponse;
import com.fleetNav.domain.entities.Maintenance;
import com.fleetNav.domain.entities.Vehicle;
import com.fleetNav.domain.repositories.MaintenanceRepository;
import com.fleetNav.domain.repositories.VehicleRepository;
import com.fleetNav.infraestructure.abstract_services.IMaintenanceService;
import com.fleetNav.infraestructure.mappers.MaintenanceMapper;
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
        Maintenance maintenance = maintenanceMapper.toMaintenance(maintenanceRequest);
        Vehicle vehicle = vehicleRepository.findById(maintenanceRequest.getVehicle().getId()).orElseThrow();
        maintenance.setVehicle(vehicle);
        Maintenance saveMaintenance = maintenanceRepository.save(maintenance);
        return maintenanceMapper.toMaintenanceResponse(saveMaintenance);
    }

    @Override
    public MaintenanceResponse update(UUID id, MaintenanceRequest maintenanceRequest) {
        Maintenance existingMaintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance not found with the id" + id));
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
        return maintenance.map(maintenanceMapper::toMaintenanceResponse);
    }
}
