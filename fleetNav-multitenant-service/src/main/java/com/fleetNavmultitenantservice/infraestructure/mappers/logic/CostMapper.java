package com.fleetNavmultitenantservice.infraestructure.mappers.logic;

import com.fleetNavmultitenantservice.api.dto.request.CostRequest;
import com.fleetNavmultitenantservice.api.dto.response.CostResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Cost;
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
