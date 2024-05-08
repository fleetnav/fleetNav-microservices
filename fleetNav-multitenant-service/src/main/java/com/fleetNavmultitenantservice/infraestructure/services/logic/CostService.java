package com.fleetNavmultitenantservice.infraestructure.services.logic;

import com.fleetNavmultitenantservice.api.dto.request.CostRequest;
import com.fleetNavmultitenantservice.api.dto.response.CostResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Cost;
import com.fleetNavmultitenantservice.domain.repositories.logic.CostRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.ICostService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.CostMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CostService implements ICostService {
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private CostMapper costMapper;

    @Override
    public CostResponse create(CostRequest costRequest) {
        Cost cost = costMapper.toCost(costRequest);
        Cost saveCost = costRepository.save(cost);
        return costMapper.toCostResponse(saveCost);
    }

    @Override
    public CostResponse update(UUID uuid, CostRequest costRequest) {
        Cost existingCost = costRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException("COST", uuid));

        costMapper.updateFromCostRequest(costRequest, existingCost);
        Cost updateCost = costRepository.save(existingCost);
        return costMapper.toCostResponse(updateCost);
    }

    @Override
    public void delete(UUID uuid) {
        costRepository.deleteById(uuid);
    }

    @Override
    public Page<CostResponse> getAll(Pageable pageable) {
        Page<Cost> costPage = costRepository.findAll(pageable);
        return costPage.map(costMapper::toCostResponse);
    }

    @Override
    public Optional<CostResponse> getById(UUID uuid) {
        Optional<Cost> cost = costRepository.findById(uuid);
        if (cost.isEmpty()) throw new IdNotFoundException("COST", uuid);
        return cost.map(costMapper::toCostResponse);
    }
}
