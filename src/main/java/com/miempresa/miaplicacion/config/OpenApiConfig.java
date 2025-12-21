package com.miempresa.miaplicacion.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // 1) Definimos el esquema Bearer JWT
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        // 2) Lo registramos con un nombre (tiene que coincidir con el requirement)
        Components components = new Components()
                .addSecuritySchemes("bearerAuth", bearerScheme);

        // 3) Indicamos que por default la API requiere ese esquema
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .components(components)
                .addSecurityItem(securityRequirement)
                .info(new Info()
                        .title("Notifications API")
                        .version("1.0.0")
                        .description("API para gestionar notificaciones")
                );
    }
}
