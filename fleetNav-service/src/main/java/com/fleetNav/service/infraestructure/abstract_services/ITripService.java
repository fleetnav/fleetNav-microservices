package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.TripRequest;
import com.fleetNav.service.api.dto.response.TripResponse;

import java.util.UUID;

public interface ITripService extends CrudService<TripRequest, TripResponse, UUID> {
}
