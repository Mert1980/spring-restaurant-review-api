package com.awbd.restaurantreview.exceptions;

import java.util.UUID;

public class NotFoundException extends BaseException {
    private static final long serialVersionUID = -9222960721843445169L;

    private static final String code = "resource_not_found";

    public NotFoundException(UUID id) {
        super(code, String.format("Resource with id: '%s' was not found.", id));
    }

    public NotFoundException(String resource) {
        super(code, String.format("'%s' was not found.", resource));
    }
}
