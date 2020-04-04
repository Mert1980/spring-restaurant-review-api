package com.awbd.restaurantreview.exceptions;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
    private String code;

    private String error;
}
