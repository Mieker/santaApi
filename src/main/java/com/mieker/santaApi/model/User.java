package com.mieker.santaApi.model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mieker.santaApi.exception.UserError;
import com.mieker.santaApi.exception.UserException;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
@Getter
@Setter
public class User implements UserDetails {

    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
    private List<Gift> wantedGifts = new ArrayList<Gift>();
    private List<Gift> giftsToGive = new ArrayList<Gift>();

    //@ManyToMany
    private Set<Role> authorities = new HashSet<>();
    private boolean enabled;

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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
