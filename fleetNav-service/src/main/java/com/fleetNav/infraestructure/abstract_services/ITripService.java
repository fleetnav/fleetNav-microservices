package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.TripRequest;
import com.fleetNav.api.dto.response.TripResponse;

import java.util.UUID;

public interface ITripService extends CrudService<TripRequest, TripResponse, UUID> {
}
