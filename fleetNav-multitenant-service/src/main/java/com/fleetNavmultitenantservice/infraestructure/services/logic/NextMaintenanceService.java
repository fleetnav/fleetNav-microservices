package com.fleetNavmultitenantservice.infraestructure.services.logic;


import com.fleetNavmultitenantservice.api.dto.request.NextMaintenanceRequest;
import com.fleetNavmultitenantservice.api.dto.response.NextMaintenanceResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.NextMaintenance;
import com.fleetNavmultitenantservice.domain.repositories.logic.NextMaintenanceRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.INextMaintenanceService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.NextMaintenanceMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NextMaintenanceService implements INextMaintenanceService {
    @Autowired
    private NextMaintenanceRepository nextMaintenanceRepository;
    @Autowired
    private NextMaintenanceMapper nextMaintenanceMapper;

    @Override
    public NextMaintenanceResponse create(NextMaintenanceRequest nextMaintenanceRequest) {
        NextMaintenance nextMaintenance = nextMaintenanceMapper.toNextMaintenance(nextMaintenanceRequest);
        NextMaintenance saveNextMaintenance = nextMaintenanceRepository.save(nextMaintenance);
        return nextMaintenanceMapper.toNextMaintenanceResponse(saveNextMaintenance);
    }

    @Override
    public NextMaintenanceResponse update(UUID uuid, NextMaintenanceRequest nextMaintenanceRequest) {
        NextMaintenance existingNextMaintenance = nextMaintenanceRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException("NEXT_MAINTENANCE", uuid));

        nextMaintenanceMapper.updateFromNextMaintenanceRequest(nextMaintenanceRequest, existingNextMaintenance);
        NextMaintenance updateNextMaintenance = nextMaintenanceRepository.save(existingNextMaintenance);
        return nextMaintenanceMapper.toNextMaintenanceResponse(updateNextMaintenance);
    }

    @Override
    public void delete(UUID uuid) {
        nextMaintenanceRepository.deleteById(uuid);
    }

    @Override
    public Page<NextMaintenanceResponse> getAll(Pageable pageable) {
        Page<NextMaintenance> nextMaintenancePage = nextMaintenanceRepository.findAll(pageable);
        return nextMaintenancePage.map(nextMaintenanceMapper::toNextMaintenanceResponse);
    }

    @Override
    public Optional<NextMaintenanceResponse> getById(UUID uuid) {
        Optional<NextMaintenance> nextMaintenance = nextMaintenanceRepository.findById(uuid);
        if (nextMaintenance.isEmpty()) throw new IdNotFoundException("NEXT_MAINTENANCE", uuid);
        return nextMaintenance.map(nextMaintenanceMapper::toNextMaintenanceResponse);
    }

}
