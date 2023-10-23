package trabalho.serratec.api.Trabalho.de.API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("API trabalho final restful")
				.description("Esse é uma API desenvolvida pelo grupo 2 para o trabalho final da matéria API Restful do SERRATEC")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.google.com/")
				.version("1.0.0")
				.build();
		return apiInfo;
	}
}
