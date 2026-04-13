package com.example.projeto_integrador.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Prontuário para Pets")
                        .description("API REST para gerenciamento de prontuários veterinários")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("João neto")
                                .email("joaonetoads25@gmail.com")
                        )
                );
    }
}