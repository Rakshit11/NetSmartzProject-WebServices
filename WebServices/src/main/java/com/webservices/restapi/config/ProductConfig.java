package com.webservices.restapi.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class ProductConfig {

    @Bean
   
    public Docket postsApi() { 
        return new Docket(DocumentationType.SWAGGER_2).groupName("FoodProduct")
                .apiInfo(apiInfo()).select().paths(regex("/api.*")).build();
    }
	private ApiInfo apiInfo() { 
		return new ApiInfoBuilder().title("Food Products Services")
				.description("The Documentation Generated using SWAGGER2 for our WebServices REST API")
				.termsOfServiceUrl("https://www.github.com/Rakshit11")
				.license("Rakshit11_License")
				.licenseUrl("https://www.github.com/Rakshit11").version("1.0").build();
	}
}
