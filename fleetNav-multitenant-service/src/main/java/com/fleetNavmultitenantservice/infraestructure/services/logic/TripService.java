package com.fleetNavmultitenantservice.infraestructure.services.logic;


import com.fleetNavmultitenantservice.api.dto.request.TripRequest;
import com.fleetNavmultitenantservice.api.dto.response.TripResponse;
import com.fleetNavmultitenantservice.domain.entities.logic.Route;
import com.fleetNavmultitenantservice.domain.entities.logic.Trip;
import com.fleetNavmultitenantservice.domain.entities.logic.Vehicle;
import com.fleetNavmultitenantservice.domain.repositories.logic.RouteRepository;
import com.fleetNavmultitenantservice.domain.repositories.logic.TripRepository;
import com.fleetNavmultitenantservice.domain.repositories.logic.VehicleRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.logic.ITripService;
import com.fleetNavmultitenantservice.infraestructure.mappers.logic.TripMapper;
import com.fleetNavmultitenantservice.util.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TripService implements ITripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TripMapper tripMapper;

    @Override
    public TripResponse create(TripRequest tripRequest) {
        Trip trip = tripMapper.toTrip(tripRequest);
        Route route = routeRepository.findById(tripRequest.getRouteId())
                .orElseThrow(() -> new IdNotFoundException("ROUTE", tripRequest.getRouteId()));
        Vehicle vehicle = vehicleRepository.findById(tripRequest.getVehicleId())
                .orElseThrow(() -> new IdNotFoundException("VEHICLE", tripRequest.getVehicleId()));

        trip.setRoute(route);
        trip.setVehicle(vehicle);

        Trip saveTrip = tripRepository.save(trip);
        return tripMapper.toTripResponse(saveTrip);
    }

    @Override
    public TripResponse update(UUID id, TripRequest tripRequest) {
        Trip existingTrip = tripRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("TRIP", id));

        tripMapper.updateFromTripRequest(tripRequest, existingTrip);
        Trip updateTrip = tripRepository.save(existingTrip);
        return tripMapper.toTripResponse(updateTrip);
    }

    @Override
    public void delete(UUID uuid) {
        tripRepository.deleteById(uuid);
    }

    @Override
    public Page<TripResponse> getAll(Pageable pageable) {
        Page<Trip> tripPage = tripRepository.findAll(pageable);
        return tripPage.map(tripMapper::toTripResponse);
    }

    @Override
    public Optional<TripResponse> getById(UUID uuid) {
        Optional<Trip> trip = tripRepository.findById(uuid);
        if (trip.isEmpty()) throw new IdNotFoundException("TRIP", uuid);
        return trip.map(tripMapper::toTripResponse);
    }
}
