package com.awbd.restaurantreview.security.jwt;

import java.util.Map;
import java.util.Set;

public interface JwtHandler {
    String createToken(String email, Set<String> authorities);
    Map<String, Object> parseToken(String token);
}
