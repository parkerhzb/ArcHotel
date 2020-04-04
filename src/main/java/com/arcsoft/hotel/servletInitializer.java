package com.arcsoft.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class servletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //Application的类名
        return application.sources(HotelApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }
}
