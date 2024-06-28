package com.hufshackerton.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI FaceMindAPI() {
        Info info = new Info()
                .title("HUFS Summer Hackerton API")
                .description("HUFS Summer Hackerton API 명세서")
                .version("1.0.0");

        String memberId = "memberId";
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(memberId, new SecurityScheme()
                        .name(memberId)
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name("memberId"));
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(memberId);


        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}