package ru.vasadm.goodsList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SpringfoxConfig {
    public static final String PRODUCT_TAG = "Product API";
    public static final String LIST_TAG = "List API";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .tags(
                        new Tag(PRODUCT_TAG, "Api for product repo"),
                        new Tag(LIST_TAG, "Api for list repo"))
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Goods list API",
                "API for goods list",
                "0.1",
                "https://termsofservice.vasadm.ru",
                new Contact("API owner", "https://vasadm.ru", "ershovvasiliy174@gmail.com"),
                "api_license",
                "https://license.vasadm.ru",
                new ArrayList<>()
        );
    }
}
