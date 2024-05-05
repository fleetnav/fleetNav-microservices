package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.api.dto.response.NextMaintenanceResponse;

import java.util.UUID;

public interface INextMaintenanceService extends CrudService<NextMaintenanceRequest, NextMaintenanceResponse, UUID> {
}
