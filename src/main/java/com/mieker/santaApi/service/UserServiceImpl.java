package com.mieker.santaApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mieker.santaApi.exception.UserError;
import com.mieker.santaApi.exception.UserException;
import com.mieker.santaApi.model.Gift;
import com.mieker.santaApi.model.User;
import com.mieker.santaApi.repository.GiftRepository;
import com.mieker.santaApi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GiftRepository giftRepository;

    public UserServiceImpl(UserRepository userRepository, GiftRepository giftRepository) {
        this.userRepository = userRepository;
        this.giftRepository = giftRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User userFromDb = userRepository.findByUserId(userId);
        if (userFromDb == null) {
            throw new UserException(UserError.USER_NOT_EXIST);
        } else {
            userRepository.delete(userFromDb);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        if (userRepository.findByUserId(userId) == null) {
            throw new UserException(UserError.USER_NOT_EXIST);
        } else {
            return userRepository.findByUserId(userId);
        }
    }

    @Override
    public User putUser(String userId, User user) {

        User userFromDb = userRepository.findByUserId(userId);
        userFromDb.setNickname(user.getNickname());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setWantedGifts(user.getWantedGifts());
        userFromDb.setGiftsToGive(user.getGiftsToGive());

        return userRepository.save(userFromDb);
    }

    @Override
    public User patchUser(String userId, User user) {
        User userFromDb = userRepository.findByUserId(userId);
        if (!user.getNickname().isBlank() && user.getNickname() != null) {
            userFromDb.setNickname(user.getNickname());
        }
        if (!user.getEmail().isBlank() && user.getEmail() != null) {
            userFromDb.setEmail(user.getEmail());
        }
        if (!user.getPassword().isBlank() && user.getPassword() != null) {
            userFromDb.setPassword(user.getPassword());
        }
        return userRepository.save(userFromDb);
    }

    @Override
    public User addWantedGift(String userId, String giftId) {

        Gift giftToAdd = giftRepository.findByGiftId(giftId);
        User userFromDb = userRepository.findByUserId(userId);

        userFromDb.addGiftToWantedGiftList(giftToAdd);
        return userRepository.save(userFromDb);

    }

    @Override
    public User removeFromWantedGift(String userId, String giftId) {
        Gift giftToRemove = giftRepository.findByGiftId(giftId);
        User userFromDb = userRepository.findByUserId(userId);

        userFromDb.removeGiftFromWantedGiftList(giftToRemove);
        return userRepository.save(userFromDb);
    }

    @Override
    public Gift reserveGift(String userId, String giftId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeFromReservedGift(String userId, String giftId) {
        // TODO Auto-generated method stub

    }

}
