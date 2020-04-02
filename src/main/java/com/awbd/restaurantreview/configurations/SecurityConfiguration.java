package com.awbd.restaurantreview.configurations;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable();

        http.headers()
            .frameOptions()
            .disable();

        http.cors()
            .disable();

        http.authorizeRequests()
            .antMatchers("/h2-console/**")
            .permitAll();
    }
}
