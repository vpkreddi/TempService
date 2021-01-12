package com.test.openapi;

import org.neo4j.driver.internal.InternalDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableNeo4jAuditing
@EnableFeignClients
@EnableTransactionManagement
@EnableSwagger2
public class TempServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempServiceApplication.class, args);
	}

	
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.test.openapi.controller"))              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	
	@Bean
	PlatformTransactionManager transactionManager(@Autowired InternalDriver driver) {
		return new Neo4jTransactionManager(driver);
	}
}
