package com.fleetNavmultitenantservice.infraestructure.mappers.logic;


import com.fleetNavmultitenantservice.api.dto.request.RouteRequest;
import com.fleetNavmultitenantservice.api.dto.response.RouteResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Route;
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
