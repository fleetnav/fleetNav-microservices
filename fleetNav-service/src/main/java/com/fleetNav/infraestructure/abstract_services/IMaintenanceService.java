package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.MaintenanceRequest;
import com.fleetNav.api.dto.response.MaintenanceResponse;

import java.util.UUID;

public interface IMaintenanceService extends CrudService<MaintenanceRequest, MaintenanceResponse, UUID> {
}
