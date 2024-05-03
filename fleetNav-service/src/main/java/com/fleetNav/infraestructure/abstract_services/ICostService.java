package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.CostRequest;
import com.fleetNav.api.dto.response.CostResponse;

import java.util.UUID;

public interface ICostService extends CrudService<CostRequest, CostResponse, UUID> {
}
