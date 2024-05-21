package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.service.api.dto.response.NextMaintenanceResponse;

import java.util.UUID;

public interface INextMaintenanceService extends CRUDService<NextMaintenanceRequest, NextMaintenanceResponse, UUID>,UpdateService<NextMaintenanceRequest, NextMaintenanceResponse, UUID> {
}
