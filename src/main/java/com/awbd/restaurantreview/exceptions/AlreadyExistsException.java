package com.awbd.restaurantreview.exceptions;

import java.util.UUID;

public class AlreadyExistsException extends BaseException {
    private static final long serialVersionUID = 2224522690805053152L;

    private static final String code = "resource_already_exists";

    public AlreadyExistsException(UUID id) {
        super(code, String.format("Resource with id: '%s' already exists.", id));
    }

    public AlreadyExistsException(String field, String value) {
        super(code, String.format("A resource with '%s': '%s' is already in use.", field, value));
    }
}
