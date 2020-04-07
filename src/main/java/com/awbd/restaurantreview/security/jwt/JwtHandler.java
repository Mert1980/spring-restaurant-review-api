package com.awbd.restaurantreview.security.jwt;

import java.util.Map;
import java.util.Set;

public interface JwtHandler {
    String create(String email, Set<String> authorities);
    Map<String, Object> parse(String token);
}
