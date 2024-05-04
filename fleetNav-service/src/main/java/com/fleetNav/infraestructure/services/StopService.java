package com.fleetNav.infraestructure.services;

import com.fleetNav.api.dto.request.StopRequest;
import com.fleetNav.api.dto.response.StopResponse;
import com.fleetNav.domain.entities.Route;
import com.fleetNav.domain.entities.Stop;
import com.fleetNav.domain.repositories.RouteRepository;
import com.fleetNav.domain.repositories.StopRepository;
import com.fleetNav.infraestructure.abstract_services.IStopService;
import com.fleetNav.infraestructure.mappers.StopMapper;
import com.fleetNav.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StopService implements IStopService {
    @Autowired
    private StopRepository stopRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private RouteService routeService;
    @Autowired
    private StopMapper stopMapper;

    @Override
    public StopResponse create(StopRequest stopRequest) {
        Stop stop = stopMapper.toStop(stopRequest);
        Route route = routeRepository.findById(stopRequest.getRouteId())
                .orElseThrow(() -> new IdNotFoundException("ROUTE", stopRequest.getRouteId()));

        stop.setRoute(route);
        Stop saveStop = stopRepository.save(stop);
        return stopMapper.toStopResponse(saveStop);
    }

    @Override
    public StopResponse update(UUID id, StopRequest stopRequest) {
        Stop existingStop = stopRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("STOP", id));

        stopMapper.updateFromStopRequest(stopRequest, existingStop);
        Stop updateStop = stopRepository.save(existingStop);
        return stopMapper.toStopResponse(updateStop);
    }

    @Override
    public void delete(UUID uuid) {
        stopRepository.deleteById(uuid);
    }

    @Override
    public Page<StopResponse> getAll(Pageable pageable) {
        Page<Stop> stopPage = stopRepository.findAll(pageable);
        return stopPage.map(stopMapper::toStopResponse);
    }

    @Override
    public Optional<StopResponse> getById(UUID uuid) {
        Optional<Stop> stop = stopRepository.findById(uuid);
        return stop.map(stopMapper::toStopResponse);
    }
}
