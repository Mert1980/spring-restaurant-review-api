package com.awbd.restaurantreview.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.*;

@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {
    private Security security;

    @Getter
    @Setter
    public class Security {
        private Jwt jwt;

        private Long refreshTokenLifetime;

        @Getter
        @Setter
        public class Jwt {
            private String secretKey;

            private Long tokenLifetime;
        }
    }
}
