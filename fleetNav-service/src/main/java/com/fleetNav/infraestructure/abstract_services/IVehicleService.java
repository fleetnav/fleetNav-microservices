package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.VehicleRequest;
import com.fleetNav.api.dto.response.VehicleResponse;

import java.util.UUID;

public interface IVehicleService extends CrudService<VehicleRequest, VehicleResponse, UUID>{
}
