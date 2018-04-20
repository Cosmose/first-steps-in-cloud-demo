package co.cosmose.firststepsincloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class FirstStepsInCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstStepsInCloudApplication.class, args);
    }
}
