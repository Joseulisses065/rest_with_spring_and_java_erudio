package com.rest_with_spring_and_java_erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring API - Person")
                        .version("V1.0")
                        .description("Some description about your API")
                        .termsOfService("https://spdx.org/licenses/Apache-2.0.html")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://spdx.org/licenses/Apache-2.0.html")
                        ));
    }
}
