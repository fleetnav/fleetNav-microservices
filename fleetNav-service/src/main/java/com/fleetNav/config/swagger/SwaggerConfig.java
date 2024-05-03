package com.fleetNav.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API for manage fleet", version = "1.0", description = "Here you can find all endpoint for that."))
public class SwaggerConfig {
}
