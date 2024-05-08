package com.fleetNavmultitenantservice.infraestructure.abstract_services.logic;

import com.fleetNavmultitenantservice.api.dto.request.StopRequest;
import com.fleetNavmultitenantservice.api.dto.response.StopResponse;

import java.util.UUID;

public interface IStopService extends CrudService<StopRequest, StopResponse, UUID> {
}
