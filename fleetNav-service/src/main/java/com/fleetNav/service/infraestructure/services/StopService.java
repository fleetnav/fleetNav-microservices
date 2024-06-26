package com.fleetNav.service.infraestructure.services;

import com.fleetNav.service.api.dto.request.StopRequest;
import com.fleetNav.service.api.dto.response.StopResponse;
import com.fleetNav.service.domain.entities.Route;
import com.fleetNav.service.domain.entities.Stop;

import com.fleetNav.service.domain.repositories.RouteRepository;
import com.fleetNav.service.domain.repositories.StopRepository;

import com.fleetNav.service.infraestructure.abstract_services.IStopService;
import com.fleetNav.service.infraestructure.mappers.StopMapper;
import com.fleetNav.service.util.exceptions.IdNotFoundException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public StopResponse update(UUID id, StopRequest stopRequest) {
        System.out.println(id);
        Stop existingStop = stopRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("STOP", id));
        System.out.println(existingStop.toString());
        System.out.println(stopRequest.toString());
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
        if (stop.isEmpty()) throw new IdNotFoundException("STOP", uuid);
        return stop.map(stopMapper::toStopResponse);
    }
}
