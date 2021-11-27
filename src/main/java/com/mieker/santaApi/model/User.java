package com.mieker.santaApi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mieker.santaApi.exception.UserError;
import com.mieker.santaApi.exception.UserException;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class User {

    @Id
    private String userId;
    private String nickname;
    private String email;
    private String password;
    private List<Gift> wantedGifts = new ArrayList<Gift>();
    private List<Gift> giftsToGive = new ArrayList<Gift>();

    public void addGiftToWantedGiftList(Gift gift) {
        if (wantedGifts.size() > 0) {
            for (Gift g : wantedGifts) {
                if (gift.getGiftId().equals(g.getGiftId())) {
                    throw new UserException(UserError.GIFT_ALREADY_INCLUDED);
                }
            }
            wantedGifts.add(gift);
        } else {
            wantedGifts.add(gift);
        }
    }

    public void removeGiftFromWantedGiftList(Gift gift) {
        int giftIndex = -1;
        for (Gift g : wantedGifts) {
            if (gift.getGiftId().equals(g.getGiftId())) {
                giftIndex = wantedGifts.indexOf(g);
            }
        }
        if (giftIndex == -1) {
            throw new UserException(UserError.GIFT_NOT_EXIST);
        }
        wantedGifts.remove(giftIndex);
    }

    public void addGiftToGiftsToGiveList(Gift gift) {
        giftsToGive.add(gift);
    }
}
