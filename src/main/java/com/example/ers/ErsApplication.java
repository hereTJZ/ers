package com.example.ers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class  ErsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErsApplication.class, args);
    }

}
