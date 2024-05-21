package com.fleetNav.service.infraestructure.abstract_services;

import com.fleetNav.service.api.dto.request.StopRequest;
import com.fleetNav.service.api.dto.response.StopResponse;

import java.util.UUID;

public interface IStopService extends CRUDService<StopRequest, StopResponse, UUID>,UpdateService<StopRequest, StopResponse, UUID> {
}
