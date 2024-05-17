package com.fleetNav.service.infraestructure.services;

import com.fleetNav.service.api.dto.request.RouteRequest;
import com.fleetNav.service.api.dto.request.RouteUpdateRequest;
import com.fleetNav.service.api.dto.response.CostResponse;
import com.fleetNav.service.api.dto.response.RouteResponse;
import com.fleetNav.service.domain.entities.Cost;
import com.fleetNav.service.domain.entities.Route;
import com.fleetNav.service.domain.repositories.CostRepository;
import com.fleetNav.service.domain.repositories.RouteRepository;
import com.fleetNav.service.infraestructure.abstract_services.ICostService;
import com.fleetNav.service.infraestructure.abstract_services.IRouteService;
import com.fleetNav.service.infraestructure.mappers.RouteMapper;
import com.fleetNav.service.util.exceptions.IdNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RouteService implements IRouteService {

  @Autowired
  private RouteRepository routeRepository;

  @Autowired
  private CostRepository costRepository;

  @Autowired
  private ICostService costService;

  @Autowired
  private RouteMapper routeMapper;

  @Override
  public RouteResponse create(RouteRequest routeRequest) {
    Route route = routeMapper.toRoute(routeRequest);
    CostResponse costResponse = costService.create(routeRequest.getCost());
    Cost cost = costRepository
      .findById(costResponse.getId())
      .orElseThrow(() -> new IdNotFoundException("COST", costResponse.getId()));

    route.setCostId(cost);
    System.out.println("test 1:"+ route.toString() );
    Route saveRoute = routeRepository.save(route);
    return routeMapper.toRouteResponse(saveRoute);
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

  @Override
  public RouteResponse update(UUID uuid, RouteUpdateRequest routeUpdateRequest) {
    Route existingRoute = routeRepository
            .findById(uuid)
            .orElseThrow(() -> new IdNotFoundException("ROUTE", uuid));
    routeMapper.updateFromRouteRequest(routeUpdateRequest, existingRoute);
    Route updateRoute = routeRepository.save(existingRoute);
    return routeMapper.toRouteResponse(updateRoute);
  }
}
