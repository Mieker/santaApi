package com.mieker.santaApi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mieker.santaApi.model.Gift;

public interface GiftRepository extends MongoRepository<Gift, Long> {

    Gift findByGiftId(String giftId);
}
