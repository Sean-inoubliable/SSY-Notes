package com.ssycoding.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SsyNotesFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsyNotesFilterApplication.class, args);
    }

}
