package com.github.davidherdu.code_challenge.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableSet;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket homeBanckingBackApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.github.davidherdu.code_challenge.controller"))
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder()
                .code(500)
                .message("Internal error").build()))
            .produces(ImmutableSet.of("application/json"))
            .tags(new Tag("Packlink", "Code challenge davidherdu"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Code challenge davidherdu - Packlink")
            .version("1.0")
            .build();
    }
}
