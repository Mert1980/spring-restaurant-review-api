package com.awbd.restaurantreview.exceptions;

public abstract class BaseException extends Exception {
    private static final long serialVersionUID = -5612539404926000159L;

    private final String code;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
