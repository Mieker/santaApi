package com.mieker.santaApi;

import com.mieker.santaApi.model.Gift;
import com.mieker.santaApi.model.Role;
import com.mieker.santaApi.model.User;
import com.mieker.santaApi.repository.GiftRepository;
import com.mieker.santaApi.repository.RoleRepository;
import com.mieker.santaApi.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

import static com.mieker.santaApi.model.Role.ERole.ROLE_ADMIN;
import static com.mieker.santaApi.model.Role.ERole.ROLE_STUDENT;

@SpringBootApplication
public class SantaApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(SantaApiApplication.class, args);
    }

    @Bean
    public InitializingBean loadData(RoleRepository roleRepository) {
        return () -> {
            if (!roleRepository.existsByRole(ROLE_STUDENT)) {
                roleRepository.save(new Role(ROLE_STUDENT));
            }
            if (!roleRepository.existsByRole(ROLE_ADMIN)) {
                roleRepository.save(new Role(ROLE_ADMIN));
            }
        };
    }
}
