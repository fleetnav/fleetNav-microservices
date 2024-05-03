package com.fleetNav.infraestructure.abstract_services;

import com.fleetNav.api.dto.request.StopRequest;
import com.fleetNav.api.dto.response.StopResponse;

import java.util.UUID;

public interface IStopService extends CrudService<StopRequest, StopResponse, UUID> {
}
