package com.awbd.restaurantreview.security.jwt;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.interfaces.Claim;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.awbd.restaurantreview.models.JsonWebTokenModel;
import com.awbd.restaurantreview.models.LoginModel;
import com.awbd.restaurantreview.security.RefreshTokenHandler;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String REFRESH_TOKEN_COOKIE_NAME = "refresh-token";
    private final AuthenticationManager authenticationManager;
    private final JwtHandler jwtHandler;
    private final RefreshTokenHandler refreshTokenHandler;

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtHandler jwtHandler, RefreshTokenHandler refreshTokenHandler) {
        this.authenticationManager = authenticationManager;
        this.jwtHandler = jwtHandler;
        this.refreshTokenHandler = refreshTokenHandler;
        setFilterProcessesUrl(LOGIN_ENDPOINT);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginModel loginModel = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginModel.getEmail(),
                        loginModel.getPassword(),
                        new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        User authenticatedUser = (User)authentication.getPrincipal();
        Set<String> authorities = AuthorityUtils.authorityListToSet(authenticatedUser.getAuthorities());
        String token = jwtHandler.createToken(authenticatedUser.getUsername(), authorities);
        Long expires = null;
        Map<String,Object>  tokenPayload = jwtHandler.parseToken(token);
        if(tokenPayload != null) {
            expires = ((Claim)tokenPayload.get("exp")).asLong();
        }

        try {
            String refreshToken = refreshTokenHandler.createRefreshToken(authenticatedUser.getUsername());
            response.addCookie(new Cookie(REFRESH_TOKEN_COOKIE_NAME, refreshToken));
        } catch (Exception e) {
            logger.info("Could not create refresh token.");
        }

        JsonWebTokenModel jsonWebTokenModel = new JsonWebTokenModel(token, expires);
        String responseBody = new ObjectMapper().writeValueAsString(jsonWebTokenModel);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write(responseBody);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
