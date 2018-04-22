package com.rafaeldbl.wsproducts;

import com.rafaeldbl.wsproducts.config.SecurityConfig;
import com.rafaeldbl.wsproducts.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.rafaeldbl.wsproducts.repository")
@EntityScan(basePackages = "com.rafaeldbl.wsproducts.model")
@ComponentScan(basePackages = "com.rafaeldbl.wsproducts.service")
@Import({SwaggerConfig.class, SecurityConfig.class})
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
}
