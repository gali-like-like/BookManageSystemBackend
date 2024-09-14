package com.sky.confiig;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class Knife4jConfig {

	@Bean
	public GroupedOpenApi adminApi() {
		return GroupedOpenApi.builder()
				.group("app-api")
				.pathsToMatch("/**")
				.packagesToScan("com.sky.vo")
				.build();
	}
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(
				new Info()
				.title("图书管理系统后端接口文档")
				.description("描述图书管理系统有哪些接口，接口如何调用等等")
				.version("v1.0.0")
				.contact(new Contact().name("galiLikeLike").email("galiLikeLike@163.com"))
				.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}
