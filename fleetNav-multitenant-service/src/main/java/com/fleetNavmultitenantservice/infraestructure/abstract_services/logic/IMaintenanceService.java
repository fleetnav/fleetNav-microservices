package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;


import com.fleetNavmultitenantservice.api.dto.request.MaintenanceRequest;
import com.fleetNavmultitenantservice.api.dto.response.MaintenanceResponse;

import java.util.UUID;

public interface IMaintenanceService extends CrudService<MaintenanceRequest, MaintenanceResponse, UUID> {
}
