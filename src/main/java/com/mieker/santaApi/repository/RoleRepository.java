package com.mieker.santaApi.repository;

import com.mieker.santaApi.model.Role;
import com.mieker.santaApi.model.Role.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Long> {

    boolean existsByRole(ERole role);
    Role findByRole(ERole role);
}
