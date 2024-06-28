package com.hufshackerton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class HufsHackertonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HufsHackertonApplication.class, args);
    }

}
