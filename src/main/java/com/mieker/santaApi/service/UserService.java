package com.mieker.santaApi.service;

import java.util.List;

import com.mieker.santaApi.model.Gift;
import com.mieker.santaApi.model.User;

public interface UserService {

    User registerUser(User user);

    void deleteUser(String userId);

    List<User> getAllUsers();

    User getUserById(String userId);

    User putUser(String userId, User user);

    User patchUser(String userId, User user);

    User addWantedGift(String userId, String giftId);

    User removeFromWantedGift(String userId, String giftId);

    User reserveGift(String userId, String giftId);

    void removeFromReservedGift(String userId, String giftId);

}
