package com.fleetNav.infraestructure.services;

import com.fleetNav.api.dto.request.VehicleStatusRequest;
import com.fleetNav.domain.entities.VehicleStatus;
import com.fleetNav.api.dto.response.VehicleStatusResponse;
import com.fleetNav.domain.repositories.VehicleStatusRepository;
import com.fleetNav.infraestructure.abstract_services.IVehicleStatusService;
import com.fleetNav.infraestructure.mappers.VehicleStatusMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleStatusService implements IVehicleStatusService {
    @Autowired
    private final VehicleStatusRepository vehicleStatusRepository;
    @Autowired
    private final VehicleStatusMapper vehicleStatusMapper;

    @Override
    public VehicleStatusResponse create(VehicleStatusRequest vehicleStatusRequest) {
        VehicleStatus vehicleStatus = vehicleStatusMapper.toVehicleStatus(vehicleStatusRequest);
        VehicleStatus saveVehicleStatus = vehicleStatusRepository.save(vehicleStatus);
        return vehicleStatusMapper.toVehicleStatusResponse(saveVehicleStatus);
    }

    @Override
    public VehicleStatusResponse update(UUID uuid, VehicleStatusRequest vehicleStatusRequest) {
        VehicleStatus existingVehicleStatus = vehicleStatusRepository.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("VehicleStatus not found: " + uuid));
        vehicleStatusMapper.updateFromVehicleStatusRequest(vehicleStatusRequest, existingVehicleStatus);
        VehicleStatus updateVehicleStatus = vehicleStatusRepository.save(existingVehicleStatus);
        return vehicleStatusMapper.toVehicleStatusResponse(updateVehicleStatus);
    }

    @Override
    public void delete(UUID uuid) {
        vehicleStatusRepository.deleteById(uuid);
    }

    @Override
    public Page<VehicleStatusResponse> getAll(Pageable pageable) {
        Page<VehicleStatus> vehicleStatusPage = vehicleStatusRepository.findAll(pageable);
        return vehicleStatusPage.map(vehicleStatusMapper::toVehicleStatusResponse);
    }

    @Override
    public Optional<VehicleStatusResponse> getById(UUID uuid) {
        Optional<VehicleStatus> vehicleStatus = vehicleStatusRepository.findById(uuid);
        return vehicleStatus.map(vehicleStatusMapper::toVehicleStatusResponse);
    }
}
