package com.gestion_retos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Challenges API",
                version = "1.0.0",
                description = "REST API for managing challenges, users, and enrollments.",
                termsOfService = "https://www.challenges-api.com/terms",
                contact = @Contact(
                        name = "Douglas Parrales",
                        email = "douglas.app.26@gmail.com",
                        url = "https://www.challenges-api.com/contact"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080/system/api/v1",
                        description = "Local Development Environment"
                ),
                @Server(
                        url = "https://api.challenges.com/system/api/v1",
                        description = "Production Environment"
                )
        }
)
public class SwaggerConfig {
}