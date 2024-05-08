package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;

import com.fleetNavmultitenantservice.api.dto.request.VehicleRequest;
import com.fleetNavmultitenantservice.api.dto.response.VehicleResponse;

import java.util.UUID;

public interface IVehicleService extends CrudService<VehicleRequest, VehicleResponse, UUID> {
}
