package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.VehicleStatusRequest;
import com.fleetNav.service.api.dto.response.VehicleStatusResponse;

import java.util.UUID;

public interface IVehicleStatusService extends CRUDService<VehicleStatusRequest, VehicleStatusResponse, UUID>,UpdateService<VehicleStatusRequest, VehicleStatusResponse, UUID> {
}
