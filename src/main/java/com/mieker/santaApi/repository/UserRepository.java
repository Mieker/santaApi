package com.mieker.santaApi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mieker.santaApi.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUserId(String userId);

    UserDetails findByUsername(String username);
}