package com.fleetNav.service.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Sebastian Moreno - Hector Montaña - Juan Pablo Regino",
                        url = "https://github.com/fleetnav/fleetNav-microservices",
                        email = "ralexale@gmail.com \n   juanreginopenagos@gmail.com \n sebastianmorenoecheverri@gmail.com"),
                license = @License(name = "fleetnav", url = "https://github.com/fleetnav/fleetNav-microservices  LICENSE"),
                title = "Documentation: fleetNav-Services Api",
                version = "1.0",
                description = "The fleetNav API documentation provides detailed information on the endpoints available to interact with the fleet management platform. Use this API to integrate vehicle tracking, route management and data analysis functionalities into your own application. The fleetNav API provides a RESTful interface that allows you to access and manipulate fleet management related data efficiently and securely."),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8088/api/v1"),
                @Server(
                description = "PROD ENV",
                url = "https://fleetnav-multitenant-service-0-0-2.onrender.com/api/v1/swagger-ui/index.html")

        },
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
