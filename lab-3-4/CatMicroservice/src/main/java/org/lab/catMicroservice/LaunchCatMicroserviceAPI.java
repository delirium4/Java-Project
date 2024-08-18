package org.lab.catMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("org.lab.dao.models")
@SpringBootApplication
@EnableJpaRepositories("org.lab.dao.repositories")
public class LaunchCatMicroserviceAPI {
    public static void main(String[] args) {
        SpringApplication.run(LaunchCatMicroserviceAPI.class, args);
    }


}
