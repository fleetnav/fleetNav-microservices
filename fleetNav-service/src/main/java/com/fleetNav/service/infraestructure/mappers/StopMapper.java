package com.fleetNav.service.infraestructure.mappers;

import com.fleetNav.service.api.dto.request.StopRequest;
import com.fleetNav.service.api.dto.response.StopResponse;
import com.fleetNav.service.domain.entities.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RouteMapper.class})
public interface StopMapper {

    @Mapping(target = "id", ignore = true)
    Stop toStop(StopRequest stopRequest);

    StopResponse toStopResponse(Stop stop);

    void updateFromStopRequest(StopRequest stopRequest, @MappingTarget Stop stop);
}
