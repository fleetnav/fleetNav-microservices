package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.RouteRequest;
import com.fleetNav.api.dto.response.RouteResponse;
import com.fleetNav.domain.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {RouteMapper.class})
public interface RouteMapper {
    default UUID mapRouteToUUID(Route route) {
        return route != null ? route.getId() : null;
    }

    Route toRoute(RouteRequest routeRequest);

    @Mapping(source = "stops",target = "stop")
    RouteResponse toRouteResponse(Route route);

}
