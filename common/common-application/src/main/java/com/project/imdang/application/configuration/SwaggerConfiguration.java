package com.project.imdang.application.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration("swaggerConfiguration")
public class SwaggerConfiguration {

    private static final String ACCESS_KEY = "Authorization";
    private static final String REFRESH_KEY = "Authorization-Refresh";

    @Bean
    public OpenAPI openAPI() {

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(ACCESS_KEY);
//                .addList(REFRESH_KEY);

        //Access Token 검증
        SecurityScheme accessTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        //Refresh Token 검증
//        SecurityScheme refreshTokenSecurityScheme = new SecurityScheme()
//                .type(SecurityScheme.Type.APIKEY)
//                .in(SecurityScheme.In.HEADER)
//                .name(refreshKey);

        Components components = new Components()
                .addSecuritySchemes(ACCESS_KEY, accessTokenSecurityScheme);

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
