package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;


import com.fleetNavmultitenantservice.api.dto.request.NextMaintenanceRequest;
import com.fleetNavmultitenantservice.api.dto.response.NextMaintenanceResponse;

import java.util.UUID;

public interface INextMaintenanceService extends CrudService<NextMaintenanceRequest, NextMaintenanceResponse, UUID> {
}
