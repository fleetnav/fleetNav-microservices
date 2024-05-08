package com.fleetNavmultitenantservice.infraestructure.services.logic;


import com.fleetNavmultitenantservice.api.dto.request.RouteRequest;
import com.fleetNavmultitenantservice.api.dto.response.CostResponse;
import com.fleetNavmultitenantservice.api.dto.response.RouteResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Cost;
import com.fleetNavmultitenantservice.domain.entities.logic.Route;
import com.fleetNavmultitenantservice.domain.repositories.logic.CostRepository;
import com.fleetNavmultitenantservice.domain.repositories.logic.RouteRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.IRouteService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.RouteMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RouteService implements IRouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private CostService costService;
    @Autowired
    private RouteMapper routeMapper;


    @Override
    public RouteResponse create(RouteRequest routeRequest) {
        Route route = routeMapper.toRoute(routeRequest);
        CostResponse costResponse = costService.create(routeRequest.getCost());
        Cost cost = costRepository.findById(costResponse.getId())
                .orElseThrow(() -> new IdNotFoundException("COST", costResponse.getId()));

        route.setCostId(cost);
        Route saveRoute = routeRepository.save(route);
        return routeMapper.toRouteResponse(saveRoute);
    }

    @Override
    public RouteResponse update(UUID uuid, RouteRequest routeRequest) {
        Route existingRoute = routeRepository.findById(uuid)
                .orElseThrow(() -> new IdNotFoundException("ROUTE", uuid));
        routeMapper.updateFromRouteRequest(routeRequest, existingRoute);
        Route updateRoute = routeRepository.save(existingRoute);
        return routeMapper.toRouteResponse(updateRoute);
    }

    @Override
    public void delete(UUID uuid) {
        routeRepository.deleteById(uuid);
    }

    @Override
    public Page<RouteResponse> getAll(Pageable pageable) {
        Page<Route> routePage = routeRepository.findAll(pageable);
        return routePage.map(routeMapper::toRouteResponse);
    }

    @Override
    public Optional<RouteResponse> getById(UUID uuid) {
        Optional<Route> route = routeRepository.findById(uuid);
        if (route.isEmpty()) throw new IdNotFoundException("ROUTE", uuid);
        return route.map(routeMapper::toRouteResponse);
    }
}
