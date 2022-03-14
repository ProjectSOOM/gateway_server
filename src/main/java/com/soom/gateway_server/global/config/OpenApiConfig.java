package com.soom.gateway_server.global.config;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public CommandLineRunner openApi(RouteDefinitionLocator locator, SwaggerUiConfigParameters parameters) {
        return args -> locator
                .getRouteDefinitions().collectList().block().stream()
                .map(RouteDefinition::getId)
                .filter(id -> id.matches(".*-api"))
                .forEach(parameters::addGroup);
    }
}
