package com.rest_with_spring_and_java_erudio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
    @Value("${cors.originPatterns:http://localhost:8080}")
    private String corsOriginPatterns = "";

    /*VIA QUERY PARAM http://localhost:8080/api/v1/persons/5?mediaType=json
        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorParameter(true)
                    .parameterName("mediaType").ignoreAcceptHeader(true)
                    .useRegisteredExtensionsOnly(false)
                    .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML);

        }*/
    //VIA HEADER PARAM http://localhost:8080/api/v1/persons/5
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
        //.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);


    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins= corsOriginPatterns.split(",");
        registry.addMapping("/**")
        //        .allowedMethods("GET","POST","PUT","PATCH","DELETE");
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }
}
