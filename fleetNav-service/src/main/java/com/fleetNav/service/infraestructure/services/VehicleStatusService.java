package com.fleetNav.service.infraestructure.services;

import com.fleetNav.service.api.dto.request.VehicleStatusRequest;
import com.fleetNav.service.domain.entities.VehicleStatus;
import com.fleetNav.service.api.dto.response.VehicleStatusResponse;
import com.fleetNav.service.domain.repositories.VehicleStatusRepository;
import com.fleetNav.service.infraestructure.abstract_services.IVehicleStatusService;
import com.fleetNav.service.infraestructure.mappers.VehicleStatusMapper;
import com.fleetNav.service.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleStatusService implements IVehicleStatusService {
    @Autowired
    private VehicleStatusRepository vehicleStatusRepository;
    @Autowired
    private VehicleStatusMapper vehicleStatusMapper;

    @Override
    public VehicleStatusResponse create(VehicleStatusRequest vehicleStatusRequest) {
        VehicleStatus vehicleStatus = vehicleStatusMapper.toVehicleStatus(vehicleStatusRequest);
        VehicleStatus saveVehicleStatus = vehicleStatusRepository.save(vehicleStatus);
        return vehicleStatusMapper.toVehicleStatusResponse(saveVehicleStatus);
    }

    @Override
    public VehicleStatusResponse update(UUID uuid, VehicleStatusRequest vehicleStatusRequest) {
        VehicleStatus existingVehicleStatus = vehicleStatusRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException("VEHICLE_STATUS", uuid));

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
        if (vehicleStatus.isEmpty()) throw new IdNotFoundException("VEHICLE_STATUS", uuid);
        return vehicleStatus.map(vehicleStatusMapper::toVehicleStatusResponse);
    }
}
