package com.example.dashboard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "DashBoard Application",
                description = "메모를 작성과 메모 AI 요약 생성 및 저장이 가능한 어플리케이션",
                version = "1.0.0"
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String[] packages = {"com.example.dashboard"};
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan(packages)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(
                new Components().addSecuritySchemes(
                        "JWT", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                )
        )
                .security(Collections.singletonList(new SecurityRequirement().addList("JWT")));
    }
}
