package com.awbd.restaurantreview.configurations;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.awbd.restaurantreview.security.UserDetailsServiceImpl;
import com.awbd.restaurantreview.security.jwt.JwtAuthenticationFilter;
import com.awbd.restaurantreview.security.jwt.JwtAuthorizationFilter;
import com.awbd.restaurantreview.security.jwt.JwtHandler;
import com.awbd.restaurantreview.security.jwt.JwtHandlerImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, ApplicationProperties applicationProperties) {
        this.userDetailsService = userDetailsService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.cors().disable();

        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth/sign-up", "/h2-console/**", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(jwtAuthenticationFilter())
            .addFilter(jwtAuthorizationFilter())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), new JwtHandlerImpl(applicationProperties));
    }

    private JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), new JwtHandlerImpl(applicationProperties));
    }
}
