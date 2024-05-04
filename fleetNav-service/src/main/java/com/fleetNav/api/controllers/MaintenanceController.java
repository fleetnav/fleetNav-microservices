package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.MaintenanceRequest;
import com.fleetNav.api.dto.response.MaintenanceResponse;
import com.fleetNav.infraestructure.abstract_services.IMaintenanceService;
import com.fleetNav.infraestructure.services.MaintenanceService;
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
@RequestMapping("/maintenances")
@AllArgsConstructor
public class MaintenanceController {
    @Autowired
    private final IMaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceResponse> saveMaintenance(@RequestBody MaintenanceRequest maintenanceRequest) {
        return ResponseEntity.ok(maintenanceService.create(maintenanceRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@PathVariable UUID id, @RequestBody MaintenanceRequest maintenanceRequest) {
        return ResponseEntity.ok(maintenanceService.update(id, maintenanceRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable UUID id) {
        maintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<MaintenanceResponse>> getAllMaintenances(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(maintenanceService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MaintenanceResponse>> getMaintenance(@PathVariable UUID id) {
        return ResponseEntity.ok(maintenanceService.getById(id));
    }
}
