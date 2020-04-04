package com.awbd.restaurantreview.exceptions;

public class InvalidInputException extends BaseException {
    private static final long serialVersionUID = 3495808256953784325L;

    private static final String code = "invalid_input";

    public InvalidInputException(String field) {
        super(code, String.format("Invalid %s.", field));
    }
}
