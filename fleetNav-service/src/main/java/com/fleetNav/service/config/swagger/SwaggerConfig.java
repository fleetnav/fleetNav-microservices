package com.fleetNav.service.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Documentation: fleetNav-Services Api",
                version = "1.0",
                description = "The fleetNav API documentation provides detailed information on the endpoints available to " +
                        "interact with the fleet management platform. Use this API to integrate vehicle tracking, route " +
                        "management and data analysis functionalities into your own application. The fleetNav API provides a " +
                        "RESTful interface that allows you to access and manipulate fleet management related data efficiently " +
                        "and securely."
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT token from nest authentication service",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
