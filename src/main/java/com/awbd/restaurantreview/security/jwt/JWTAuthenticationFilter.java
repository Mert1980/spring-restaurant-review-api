package com.awbd.restaurantreview.security.jwt;

import java.util.ArrayList;
import java.util.Set;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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

import com.awbd.restaurantreview.models.SignIn;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final AuthenticationManager authenticationManager;
    private final JwtHandler jwtHandler;

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtHandler jwtHandler) {
        this.authenticationManager = authenticationManager;
        this.jwtHandler = jwtHandler;
        setFilterProcessesUrl(LOGIN_ENDPOINT);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            SignIn signInModel = new ObjectMapper()
                    .readValue(req.getInputStream(), SignIn.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        signInModel.getEmail(),
                        signInModel.getPassword(),
                        new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        User authenticatedUser = (User)auth.getPrincipal();
        Set<String> authorities = AuthorityUtils.authorityListToSet(authenticatedUser.getAuthorities());
        String token = jwtHandler.createToken(authenticatedUser.getUsername(), authorities);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
