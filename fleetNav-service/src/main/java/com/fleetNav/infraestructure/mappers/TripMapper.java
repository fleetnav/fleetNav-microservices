package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.TripRequest;
import com.fleetNav.api.dto.response.TripResponse;
import com.fleetNav.domain.entities.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {TripMapper.class,CommentMapper.class})
public interface TripMapper {
    @Mapping(target = "id", ignore = true)
    Trip toTrip(TripRequest tripRequest);

    TripResponse toTripResponse(Trip trip);

    void updateFromTripRequest(TripRequest tripRequest, @MappingTarget Trip trip);
}
