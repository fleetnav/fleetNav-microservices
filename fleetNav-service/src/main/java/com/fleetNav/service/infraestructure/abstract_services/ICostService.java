package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.CostRequest;
import com.fleetNav.service.api.dto.response.CostResponse;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICostService extends CreateReadDeleteService<CostRequest, CostResponse, UUID>, UpdateService<CostRequest, CostResponse, UUID> {
}
