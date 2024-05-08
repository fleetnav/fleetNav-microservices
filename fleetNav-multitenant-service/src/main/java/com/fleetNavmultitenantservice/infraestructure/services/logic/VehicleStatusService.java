package com.fleetNavmultitenantservice.infraestructure.services.logic;

import com.fleetNavmultitenantservice.api.dto.request.VehicleStatusRequest;
import com.fleetNavmultitenantservice.api.dto.response.VehicleStatusResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.VehicleStatus;
import com.fleetNavmultitenantservice.domain.repositories.logic.VehicleStatusRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.IVehicleStatusService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.VehicleStatusMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
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
