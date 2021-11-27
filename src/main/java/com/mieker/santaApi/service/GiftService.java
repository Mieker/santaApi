package com.mieker.santaApi.service;

import java.util.List;

import com.mieker.santaApi.model.Gift;

public interface GiftService {

    Gift createGift(Gift gift);

    void deleteGift(String giftId);

    List<Gift> getAllGifts();

    Gift getGiftById(String giftId);

    Gift putGift(String giftId, Gift gift);

    Gift patchGift(String giftId, Gift gift);

}
