package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.TripRequest;
import com.fleetNav.api.dto.response.TripResponse;
import com.fleetNav.infraestructure.abstract_services.ITripService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
public class TripController {
    @Autowired
    private final ITripService tripService;

    @PostMapping
    public ResponseEntity<TripResponse> saveTrip(@RequestBody TripRequest tripRequest) {
        return ResponseEntity.ok(tripService.create(tripRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> updateTrip(@PathVariable UUID id, @RequestBody TripRequest tripRequest) {
        return ResponseEntity.ok(tripService.update(id, tripRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID id) {
        tripService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TripResponse>> getAllTrips(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(tripService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TripResponse>> getTrip(@PathVariable UUID id) {
        return ResponseEntity.ok(tripService.getById(id));
    }
}
