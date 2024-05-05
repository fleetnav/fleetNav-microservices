package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.RouteRequest;
import com.fleetNav.api.dto.response.RouteResponse;
import com.fleetNav.domain.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CostMapper.class, StopMapper.class})
public interface RouteMapper {
    @Mappings({
            @Mapping(source = "stops", target = "stop"),
            @Mapping(source = "costId", target = "cost")
    })
    RouteResponse toRouteResponse(Route route);

    @Mapping(target = "id", ignore = true)
    Route toRoute(RouteRequest routeRequest);

    void updateFromRouteRequest(RouteRequest routeRequest, @MappingTarget Route route);

}
