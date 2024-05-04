package com.fleetNav.infraestructure.services;

import com.fleetNav.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.api.dto.response.NextMaintenanceResponse;
import com.fleetNav.domain.entities.NextMaintenance;
import com.fleetNav.domain.repositories.NextMaintenanceRepository;
import com.fleetNav.infraestructure.abstract_services.INextMaintenanceService;
import com.fleetNav.infraestructure.mappers.NextMaintenanceMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NextMaintenanceService implements INextMaintenanceService {
    @Autowired
    private final NextMaintenanceRepository nextMaintenanceRepository;
    @Autowired
    private final NextMaintenanceMapper nextMaintenanceMapper;

    @Override
    public NextMaintenanceResponse create(NextMaintenanceRequest nextMaintenanceRequest) {
        NextMaintenance nextMaintenance = nextMaintenanceMapper.toNextMaintenance(nextMaintenanceRequest);
        NextMaintenance saveNextMaintenance = nextMaintenanceRepository.save(nextMaintenance);
        return nextMaintenanceMapper.toNextMaintenanceResponse(saveNextMaintenance);
    }

    @Override
    public NextMaintenanceResponse update(UUID uuid, NextMaintenanceRequest nextMaintenanceRequest) {
        NextMaintenance existingNextMaintenance = nextMaintenanceRepository.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("NextMaintenance not found: " + uuid));
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
        return nextMaintenance.map(nextMaintenanceMapper::toNextMaintenanceResponse);
    }

}
