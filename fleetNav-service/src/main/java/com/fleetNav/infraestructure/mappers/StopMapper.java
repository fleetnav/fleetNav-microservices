package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.StopRequest;
import com.fleetNav.api.dto.request.TripRequest;
import com.fleetNav.api.dto.response.StopResponse;
import com.fleetNav.domain.entities.Stop;
import com.fleetNav.domain.entities.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {RouteMapper.class})
public interface StopMapper {

    @Mapping(target = "id", ignore = true)
    Stop toStop(StopRequest stopRequest);

    StopResponse toStopResponse(Stop stop);

    void updateFromStopRequest(StopRequest stopRequest, @MappingTarget Stop stop);
}
