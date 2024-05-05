package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.CostRequest;
import com.fleetNav.api.dto.response.CostResponse;
import com.fleetNav.domain.entities.Cost;
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
