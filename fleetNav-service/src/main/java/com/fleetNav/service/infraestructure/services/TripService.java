package com.fleetNav.service.infraestructure.services;

import com.fleetNav.service.api.dto.request.TripRequest;

import com.fleetNav.service.api.dto.response.TripResponse;

import com.fleetNav.service.domain.entities.Route;
import com.fleetNav.service.domain.entities.Trip;
import com.fleetNav.service.domain.entities.Vehicle;
import com.fleetNav.service.domain.repositories.RouteRepository;
import com.fleetNav.service.domain.repositories.TripRepository;
import com.fleetNav.service.domain.repositories.VehicleRepository;
import com.fleetNav.service.infraestructure.abstract_services.ITripService;
import com.fleetNav.service.infraestructure.mappers.TripMapper;
import com.fleetNav.service.util.exceptions.IdNotFoundException;
import org.hibernate.Hibernate;
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
        System.out.println(trip.toString());
        Route route = routeRepository.findById(tripRequest.getRouteId())
                .orElseThrow(() -> new IdNotFoundException("ROUTE", tripRequest.getRouteId()));
        System.out.println(route.toString());
        Vehicle vehicle = vehicleRepository.findById(tripRequest.getVehicleId())
                .orElseThrow(() -> new IdNotFoundException("VEHICLE", tripRequest.getVehicleId()));
        System.out.println("Imprimir");
        System.out.println(vehicle.toString());
        trip.setRoute(route);
        trip.setVehicle(vehicle);
        System.out.println(trip.toString());
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
        tripPage.forEach(trip -> {
            Hibernate.initialize(trip.getComments());
        });
        return tripPage.map(tripMapper::toTripResponse);
    }

    @Override
    public Optional<TripResponse> getById(UUID uuid) {
        Optional<Trip> trip = tripRepository.findById(uuid);
        if (trip.isEmpty()) throw new IdNotFoundException("TRIP", uuid);
        return trip.map(tripMapper::toTripResponse);
    }
}
