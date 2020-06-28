package br.com.clover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
public class CloverBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		AppProperties appProperties = new AppProperties();
		System.out.println(appProperties.getBanner());
		System.out.println(appProperties.getVersion());
		System.out.println("Use Swagger @: http://localhost:9897/clover/swagger-ui.html");

		SpringApplication.run(CloverBackendApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CloverBackendApplication.class);
	}
}
