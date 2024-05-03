package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.StopRequest;
import com.fleetNav.api.dto.response.StopResponse;
import com.fleetNav.domain.entities.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {StopMapper.class})
public interface StopMapper {

    @Mapping(target = "id", ignore = true)
    Stop toStop(StopRequest stopRequest);

    StopResponse toStopResponse(Stop stop);

}
