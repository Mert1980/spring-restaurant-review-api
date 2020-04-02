package com.awbd.restaurantreview.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.awbd.restaurantreview.logging.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class LogginConfiguration {
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
