package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.TripRequest;
import com.fleetNav.service.api.dto.response.TripResponse;

import java.util.UUID;

public interface ITripService extends CRUDService<TripRequest, TripResponse, UUID>, UpdateService<TripRequest, TripResponse, UUID> {
}
