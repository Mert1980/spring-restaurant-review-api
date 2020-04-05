package com.awbd.restaurantreview.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.*;

@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Getter
@Setter
public class ApplicationProperties {
    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {
        private final Jwt jwt = new Jwt();

        private Long refreshTokenLifetime;

        @Getter
        @Setter
        public static class Jwt {
            private String secretKey;

            private Long tokenLifetime;
        }
    }
}
