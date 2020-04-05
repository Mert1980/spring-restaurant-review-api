package com.awbd.restaurantreview.exceptions;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionsResponse {
    private String code;

    private List<String> errors;
}
