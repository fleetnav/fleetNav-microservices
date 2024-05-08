package com.fleetNavmultitenantservice.infraestructure.mappers.logic;

import com.fleetNavmultitenantservice.api.dto.request.TripRequest;
import com.fleetNavmultitenantservice.api.dto.response.TripResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TripMapper.class,CommentMapper.class})
public interface TripMapper {
    @Mapping(target = "id", ignore = true)
    Trip toTrip(TripRequest tripRequest);

    @Mapping(target = "route.cost", source = "route.costId")
    @Mapping(target = "route.stop", source = "route.stops")
    TripResponse toTripResponse(Trip trip);

    void updateFromTripRequest(TripRequest tripRequest, @MappingTarget Trip trip);
}
