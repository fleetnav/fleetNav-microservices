package com.fleetNav.service.infraestructure.abstract_services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CRUDService<Request, Response, Id> {
    Response create(Request request);

    void delete(Id id);

    Page<Response> getAll(Pageable pageable);

    Optional<Response> getById(Id id);
}
