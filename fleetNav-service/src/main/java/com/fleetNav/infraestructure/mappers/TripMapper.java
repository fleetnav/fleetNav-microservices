package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.TripRequest;
import com.fleetNav.api.dto.response.TripResponse;
import com.fleetNav.domain.entities.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TripMapper.class,CommentMapper.class})
public interface TripMapper {
    @Mapping(target = "id", ignore = true)
    Trip toTrip(TripRequest tripRequest);

    @Mapping(target = "route.cost", source = "route.costId")
    @Mapping(target = "route.stop", source = "route.stops")
    TripResponse toTripResponse(Trip trip);

    void updateFromTripRequest(TripRequest tripRequest, @MappingTarget Trip trip);
}
