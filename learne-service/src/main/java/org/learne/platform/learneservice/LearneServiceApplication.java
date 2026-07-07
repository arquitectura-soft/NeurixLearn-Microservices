package org.learne.platform.learneservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LearneServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearneServiceApplication.class, args);
    }

}
