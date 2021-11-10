package com.xventure.petproject.petprojectbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Pet Project API",version = "1.0",description = "Can use this API for add and view users"))
public class PetProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetProjectBackendApplication.class, args);
    }

}
