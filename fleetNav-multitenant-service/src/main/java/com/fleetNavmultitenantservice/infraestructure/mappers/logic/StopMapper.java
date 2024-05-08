package com.fleetNavmultitenantservice.infraestructure.mappers.logic;


import com.fleetNavmultitenantservice.api.dto.request.StopRequest;
import com.fleetNavmultitenantservice.api.dto.response.StopResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Stop;
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
