package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.RouteRequest;
import com.fleetNav.api.dto.response.RouteResponse;

import java.util.UUID;

public interface IRouteService extends CrudService<RouteRequest, RouteResponse, UUID> {
}
