package com.gestion_retos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Challenges API",
                description = "API for Management Challenges!",
                termsOfService = "http://www.challenges-api.com/terms-of-Services",
                contact = @Contact(
                        name = "Douglas Parrales",
                        url = "http://www.challenge-api/contac",
                        email = "douglas.app.26@gmail.com"
                ),
                license = @License(
                        name = "License Challenge API",
                        url = "http://www.challenge-api/license"
                ),
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080/system/api/v1/",
                        description = "LOCAL ENV"
                ),
                @Server(
                        url = "http://challenge-api/system/api/v1/",
                        description = "PROD ENV"
                ),
        }
)
public class SwaggerConfig {
}