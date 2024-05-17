package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.VehicleRequest;
import com.fleetNav.service.api.dto.request.VehicleUpdateRequest;
import com.fleetNav.service.api.dto.response.VehicleResponse;

import java.util.UUID;

public interface IVehicleService extends CreateReadDeleteService<VehicleRequest, VehicleResponse, UUID>, UpdateService<VehicleUpdateRequest, VehicleResponse, UUID>{
}
