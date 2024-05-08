package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;

import com.fleetNavmultitenantservice.api.dto.request.RouteRequest;
import com.fleetNavmultitenantservice.api.dto.response.RouteResponse;

import java.util.UUID;

public interface IRouteService extends CrudService<RouteRequest, RouteResponse, UUID> {
}
