package com.gt.gamexchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameXChangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameXChangerApplication.class, args);
    }
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.gt.gamexchanger")).build();
//    }
}
