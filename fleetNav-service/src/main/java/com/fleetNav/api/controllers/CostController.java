package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.CostRequest;
import com.fleetNav.api.dto.response.CostResponse;
import com.fleetNav.infraestructure.services.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/costs")
public class CostController {
    @Autowired
    private CostService costService;

    @PostMapping
    public ResponseEntity<CostResponse> saveCost(@RequestBody CostRequest costRequest) {
        return ResponseEntity.ok(costService.create(costRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostResponse> updateCost(@PathVariable UUID id, @RequestBody CostRequest costRequest) {
        return ResponseEntity.ok(costService.update(id, costRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCost(@PathVariable UUID id) {
        costService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CostResponse>> getAllCosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(costService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CostResponse>> getCost(@PathVariable UUID id) {
        return ResponseEntity.ok(costService.getById(id));
    }
}
