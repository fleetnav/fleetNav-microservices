package com.fleetNavapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("swagger", r -> r.path("api/v1/swagger-ui/**")
                        .uri("http://localhost:8082/api/v1/swagger-ui/index.html#/"))
                .build();
    }
}
