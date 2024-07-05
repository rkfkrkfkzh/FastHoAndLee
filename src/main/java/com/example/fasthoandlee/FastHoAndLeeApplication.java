package com.example.fasthoandlee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FastHoAndLeeApplication extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(FastHoAndLeeApplication.class);
//    }

//./gradlew clean build // war, jar 생성 클린빌드

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/");
        SpringApplication.run(FastHoAndLeeApplication.class, args);
    }
}
