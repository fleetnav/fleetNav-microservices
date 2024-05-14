package com.fleetNav.service.infraestructure.mappers;

import com.fleetNav.service.api.dto.request.CostRequest;
import com.fleetNav.service.api.dto.response.CostResponse;
import com.fleetNav.service.domain.entities.Cost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CostMapper.class})
public interface CostMapper {

    @Mapping(target = "id", ignore = true)
    Cost toCost(CostRequest costRequest);

    CostResponse toCostResponse(Cost cost);

    void updateFromCostRequest(CostRequest costRequest, @MappingTarget Cost cost);
}
