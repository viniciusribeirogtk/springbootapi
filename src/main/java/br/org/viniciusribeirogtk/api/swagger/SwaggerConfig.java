package br.org.viniciusribeirogtk.api.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.org.viniciusribeirogtk.api"))
                    .paths(regex("/pessoas.*"))
                    .build()
//                .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
//                    .name("Authorization")
//                    .description("Bearer token")
//                    .modelRef(new ModelRef("string"))
//                    .parameterType("header")
//                    .required(true)
//                    .build()))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot API Test")
                .description("An example made with devdojo youtube channel course")
                .version("1.0")
                .contact(new Contact("Vinicius Ribeiro", "https://github.com/viniciusribeirogtk", "viniciusribeirosp@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .build();
    }
}
