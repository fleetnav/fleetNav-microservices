package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;

import com.fleetNavmultitenantservice.api.dto.request.VehicleStatusRequest;
import com.fleetNavmultitenantservice.api.dto.response.VehicleStatusResponse;

import java.util.UUID;

public interface IVehicleStatusService extends CrudService<VehicleStatusRequest, VehicleStatusResponse, UUID> {
}
