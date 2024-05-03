package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.RouteRequest;
import com.fleetNav.api.dto.response.RouteResponse;
import com.fleetNav.infraestructure.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteResponse> saveRoute(@RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(routeService.create(routeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable UUID id, @RequestBody RouteRequest routeRequest) {
        return ResponseEntity.ok(routeService.update(id, routeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable UUID id) {
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<RouteResponse>> getAllRoutes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(routeService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RouteResponse>> getRoute(@PathVariable UUID id) {
        return ResponseEntity.ok(routeService.getById(id));
    }
}
