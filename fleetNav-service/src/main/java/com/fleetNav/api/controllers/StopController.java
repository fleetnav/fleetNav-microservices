package com.fleetNav.api.controllers;

import com.fleetNav.api.dto.request.StopRequest;
import com.fleetNav.api.dto.response.StopResponse;
import com.fleetNav.infraestructure.services.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/stops")
public class StopController {
    @Autowired
    private StopService stopService;

    @PostMapping
    public ResponseEntity<StopResponse> saveStop(@RequestBody StopRequest stopRequest) {
        return ResponseEntity.ok(stopService.create(stopRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StopResponse> updateStop(@PathVariable UUID id, @RequestBody StopRequest stopRequest) {
        return ResponseEntity.ok(stopService.update(id, stopRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStop(@PathVariable UUID id) {
        stopService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<StopResponse>> getAllStops(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (page != 0) pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(stopService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StopResponse>> getStop(@PathVariable UUID id) {
        return ResponseEntity.ok(stopService.getById(id));
    }
}
