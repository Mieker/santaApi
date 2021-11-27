package com.mieker.santaApi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mieker.santaApi.model.User;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUserId(String userId);
}
