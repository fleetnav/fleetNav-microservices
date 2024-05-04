package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.VehicleStatusRequest;
import com.fleetNav.api.dto.response.VehicleStatusResponse;
import com.fleetNav.infraestructure.abstract_services.IVehicleStatusService;
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
@RequestMapping("/vehicleStatus")
@AllArgsConstructor
public class VehicleStatusController {
    @Autowired
    private final IVehicleStatusService vehicleStatusService;
    // We don't need http request, because this action is called for vehicle
    /*@PostMapping
    public ResponseEntity<VehicleStatusResponse> saveVehicleStatus(@RequestBody VehicleStatusRequest vehicleStatusRequest) {
        return ResponseEntity.ok(vehicleStatusService.create(vehicleStatusRequest));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<VehicleStatusResponse> updateVehicleStatus(@PathVariable UUID id, @RequestBody VehicleStatusRequest vehicleStatusRequest) {
        return ResponseEntity.ok(vehicleStatusService.update(id, vehicleStatusRequest));
    }
    //for delete a vehicle status is required delete the car first
    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleStatus(@PathVariable UUID id) {
        vehicleStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }*/

    @GetMapping
    public ResponseEntity<Page<VehicleStatusResponse>> getAllVehicleStatus(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(vehicleStatusService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VehicleStatusResponse>> getVehicleStatus(@PathVariable UUID id) {
        return ResponseEntity.ok(vehicleStatusService.getById(id));
    }
}
