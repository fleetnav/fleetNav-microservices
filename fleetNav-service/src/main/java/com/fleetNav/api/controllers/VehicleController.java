package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.VehicleRequest;
import com.fleetNav.api.dto.response.VehicleResponse;
import com.fleetNav.infraestructure.abstract_services.IVehicleService;
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
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    @Autowired
    private final IVehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> saveVehicle(@RequestBody VehicleRequest vehicleRequest) {
        return ResponseEntity.ok(vehicleService.create(vehicleRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable UUID id, @RequestBody VehicleRequest vehicleRequest) {
        return ResponseEntity.ok(vehicleService.update(id, vehicleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<VehicleResponse>> getAllVehicles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(vehicleService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VehicleResponse>> getVehicle(@PathVariable UUID id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }
}
