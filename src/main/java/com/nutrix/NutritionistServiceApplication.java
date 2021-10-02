package com.nutrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NutritionistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutritionistServiceApplication.class, args);
    }

}
