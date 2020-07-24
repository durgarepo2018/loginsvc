package com.mckesson.inferno.loginsvc.util;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Configuration
@Configuration
// Enable Swagger
@EnableSwagger2
public class SwaggerConfig {

	// http://localhost:9096/v2/api-docs
	// http://localhost:9096/swagger-ui.htm
	// Define a Docklet Bean
	
	public Docket api( ) {
		 return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(regex("/.*"))
	                .build().apiInfo(apiInfo());
	}
		// Swagger -2 
		// All the path
		// All the Apis
	
	  private ApiInfo apiInfo()
	    {
	        ApiInfo apiInfo = new ApiInfo(
	                "My Project's REST API",
	                "This is a description of your API.",
	                "version-1",
	                "API TOS",
	                "inferno@mckesson.com",
	                "API License",
	                "API License URL"
	        );
	        return apiInfo;
	    }
}
