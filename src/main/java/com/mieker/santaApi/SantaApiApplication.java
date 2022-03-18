package com.mieker.santaApi;

import com.mieker.santaApi.model.Role;
import com.mieker.santaApi.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.mieker.santaApi.model.Role.ERole.ROLE_ADMIN;
import static com.mieker.santaApi.model.Role.ERole.ROLE_USER;

@SpringBootApplication
public class SantaApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(SantaApiApplication.class, args);
    }

    @Bean
    public InitializingBean loadData(RoleRepository roleRepository) {
        return () -> {
            if (!roleRepository.existsByRole(ROLE_USER)) {
                roleRepository.save(new Role(ROLE_USER));
            }
            if (!roleRepository.existsByRole(ROLE_ADMIN)) {
                roleRepository.save(new Role(ROLE_ADMIN));
            }
        };
    }
}
