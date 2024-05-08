package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;

import com.fleetNavmultitenantservice.api.dto.request.TripRequest;
import com.fleetNavmultitenantservice.api.dto.response.TripResponse;

import java.util.UUID;

public interface ITripService extends CrudService<TripRequest, TripResponse, UUID> {
}
