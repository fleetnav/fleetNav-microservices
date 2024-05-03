package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.CommentRequest;
import com.fleetNav.api.dto.request.TripRequest;
import com.fleetNav.api.dto.response.CommentResponse;
import com.fleetNav.api.dto.response.TripResponse;
import com.fleetNav.domain.entities.Comment;
import com.fleetNav.domain.entities.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, RouteMapper.class, VehicleMapper.class})
public interface TripMapper {
    default UUID mapTripToUUID(Comment comment) {
        return comment != null ? comment.getId() : null;
    }

    @Mapping(target = "id", ignore = true)
    Trip tripToComment(TripRequest tripRequest);

    TripResponse tripTotripResponse(Trip trip);

    void updateFromTripRequest(TripRequest tripRequest, @MappingTarget Trip trip);
}
