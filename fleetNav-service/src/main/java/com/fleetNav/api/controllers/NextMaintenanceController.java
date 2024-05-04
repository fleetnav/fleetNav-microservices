package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.NextMaintenanceRequest;
import com.fleetNav.api.dto.response.NextMaintenanceResponse;
import com.fleetNav.infraestructure.services.NextMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/nextMaintenances")
public class NextMaintenanceController {
    @Autowired
    private NextMaintenanceService nextMaintenanceService;
    // We don't need http request, because this action is called for vehicle
    /*@PostMapping
    public ResponseEntity<NextMaintenanceResponse> saveNextMaintenance(@RequestBody NextMaintenanceRequest nextMaintenanceRequest) {
        return ResponseEntity.ok(nextMaintenanceService.create(nextMaintenanceRequest));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<NextMaintenanceResponse> updateNextMaintenance(@PathVariable UUID id, @RequestBody NextMaintenanceRequest nextMaintenanceRequest) {
        return ResponseEntity.ok(nextMaintenanceService.update(id, nextMaintenanceRequest));
    }
    // Just exist one way to delete the next maintenance, and this is deleting the vehicle
    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNextMaintenance(@PathVariable UUID id) {
        nextMaintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }*/

    @GetMapping
    public ResponseEntity<Page<NextMaintenanceResponse>> getAllNextMaintenances(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(nextMaintenanceService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<NextMaintenanceResponse>> getNextMaintenance(@PathVariable UUID id) {
        return ResponseEntity.ok(nextMaintenanceService.getById(id));
    }
}
