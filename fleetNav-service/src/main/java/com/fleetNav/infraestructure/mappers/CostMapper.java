package com.fleetNav.infraestructure.mappers;

import com.fleetNav.api.dto.request.CostRequest;
import com.fleetNav.api.dto.response.CostResponse;
import com.fleetNav.domain.entities.Comment;
import com.fleetNav.domain.entities.Cost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {CostMapper.class})
public interface CostMapper {
    default UUID mapCostToUUID(Cost cost) {
        return cost != null ? cost.getId() : null;
    }

    @Mapping(target = "id", ignore = true)
    Cost toCost(CostRequest costRequest);

    CostResponse toCostResponse(Cost cost);
}
