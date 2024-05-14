package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.RouteRequest;
import com.fleetNav.service.api.dto.response.RouteResponse;

import java.util.UUID;

public interface IRouteService extends CrudService<RouteRequest, RouteResponse, UUID> {
}
