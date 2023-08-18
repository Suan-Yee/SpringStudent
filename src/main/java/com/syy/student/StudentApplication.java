package com.syy.student;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
                description = "Spring Boot REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Suan",
                        email = "suansuan@gmail.com",
                        url = "------------"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "--------------------"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Student Management Documentation",
                url = ""
        )
)

public class StudentApplication {

    @Bean
    public ModelMapper modalMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

}
