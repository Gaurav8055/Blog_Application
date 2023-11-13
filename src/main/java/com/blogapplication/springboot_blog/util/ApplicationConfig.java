package com.blogapplication.springboot_blog.util;

import java.util.ArrayList;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {

	@Bean
	public Docket getDocket()
	{
		springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact("Gaurav",
				"github.com/Gaurav8055.com", "gaurav8084@gmail.com");
		List<VendorExtension> extensions = new ArrayList<>();
//		
		ApiInfo appinfo = new ApiInfo("Blog Application", " EM version 1.0", "version 1.0", "1 Year Free Service ",
				contact, "QWGDHS2376", "http//gsk123.com", extensions);

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.blogapplication.springboot_blog")).build()
				.apiInfo(appinfo).useDefaultResponseMessages(false);

	}
}
