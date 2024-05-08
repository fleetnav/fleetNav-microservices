package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;


import com.fleetNavmultitenantservice.api.dto.request.CostRequest;
import com.fleetNavmultitenantservice.api.dto.response.CostResponse;

import java.util.UUID;

public interface ICostService extends CrudService<CostRequest, CostResponse, UUID> {
}
