package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.MaintenanceRequest;
import com.fleetNav.service.api.dto.response.MaintenanceResponse;

import java.util.UUID;

public interface IMaintenanceService extends CRUDService<MaintenanceRequest, MaintenanceResponse, UUID>,UpdateService<MaintenanceRequest, MaintenanceResponse, UUID> {
}
