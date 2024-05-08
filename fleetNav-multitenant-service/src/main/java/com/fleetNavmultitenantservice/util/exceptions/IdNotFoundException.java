package com.fleetNavmultitenantservice.util.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "No records found in the entity [%s] with the specified id %s.";

    public IdNotFoundException(String entityName, UUID id) {
        super(String.format(ERROR_MESSAGE, entityName,id));
    }
}
