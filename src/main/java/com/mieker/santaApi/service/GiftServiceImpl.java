package com.mieker.santaApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mieker.santaApi.model.Gift;
import com.mieker.santaApi.repository.GiftRepository;

@Service
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;

    public GiftServiceImpl(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public Gift createGift(Gift gift) {
        return giftRepository.save(gift);
    }

    @Override
    public void deleteGift(String giftId) {
        Gift giftFromDb = giftRepository.findByGiftId(giftId);
        giftRepository.delete(giftFromDb);
    }

    @Override
    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    @Override
    public Gift getGiftById(String giftId) {
        return giftRepository.findByGiftId(giftId);
    }

    @Override
    public Gift putGift(String giftId, Gift gift) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Gift patchGift(String giftId, Gift gift) {

        Gift giftFromDb = giftRepository.findByGiftId(giftId);
        if (!gift.getName().isBlank() && gift.getName() != null) {
            giftFromDb.setName(gift.getName());
        }
        if (!gift.getDescription().isBlank() && gift.getDescription() != null) {
            giftFromDb.setDescription(gift.getDescription());
        }
        if (!gift.getLink().isBlank() && gift.getLink() != null) {
            giftFromDb.setLink(gift.getLink());
        }
        if (gift.getPrice() != null) {
            giftFromDb.setPrice(gift.getPrice());
        }
        return giftRepository.save(giftFromDb);
    }

}
