package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.VehicleStatusRequest;
import com.fleetNav.api.dto.response.VehicleStatusResponse;

import java.util.UUID;

public interface IVehicleStatusService extends CrudService<VehicleStatusRequest, VehicleStatusResponse, UUID> {
}
