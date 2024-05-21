package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.RouteRequest;
import com.fleetNav.service.api.dto.request.RouteUpdateRequest;
import com.fleetNav.service.api.dto.response.RouteResponse;

import java.util.UUID;

public interface IRouteService extends CRUDService<RouteRequest, RouteResponse, UUID>, UpdateService<RouteUpdateRequest, RouteResponse, UUID> {
}
