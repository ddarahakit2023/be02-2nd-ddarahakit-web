package com.ddarahakit.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(
                        RequestHandlerSelectors.basePackage("com.ddarahakit.web.user")
                                .or(RequestHandlerSelectors.basePackage("com.ddarahakit.web.course"))
                )
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "따라하면서 배우는 IT",
                "따라하면서 배우는 IT",
                "v1.0",
                "http://www.ddarahakit.kro.kr/",
                new Contact("따라학잇", "https://www.youtube.com/@ddarahakit", " ddarahakit@gmail.com"),
                "Licenses",
                "http://www.ddarahakit.kro.kr/",
                new ArrayList<>());
    }


}
