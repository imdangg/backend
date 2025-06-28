package com.project.imdang.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import static com.project.imdang.common.application.constant.Header.AUTHORIZATION;
import static com.project.imdang.common.application.constant.Header.BEARER;

@Configuration("swaggerConfiguration")
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(AUTHORIZATION);

        // Access Token 검증
        SecurityScheme accessTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER)
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
        Components components = new Components()
                .addSecuritySchemes(AUTHORIZATION, accessTokenSecurityScheme);
        return new OpenAPI()
                .info(apiInfo())
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    private Info apiInfo() {
        return new Info()
                .title("아파트임당 API")
                .description("아파트 임당 API 명세서")
                .version("1.0.0");
    }
}
