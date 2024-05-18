package com.fleetNavchatservice.config.swagger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        info = @Info(
        contact = @Contact(
                            name = "Sebastian Moreno - Hector Montaña - Juan Pablo Regino", 
                            url = "https://github.com/fleetnav/fleetNav-microservices", 
                            email = "ralexale@gmail.com \n   juanreginopenagos@gmail.com \n sebastianmorenoecheverri@gmail.com"),
        license = @License(name = "fleetnav", url = "https://github.com/fleetnav/fleetNav-microservices  LICENSE"),
        title = "Documentation: fleetNav-Chat-Service Api",  
        version = "1.0", 
        description = "The chat service implemented with Spring and WebSocket enables real-time communication between multiple clients. Users can send and receive messages instantly, facilitating collaboration and efficient communication."),
        servers = { 
                  @Server(
                            description = "Chat ENV",  
                            url = "http://localhost:8083")
                            
        }
)
public class SwaggerConfig {
  
}
