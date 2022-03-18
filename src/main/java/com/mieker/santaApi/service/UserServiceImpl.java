package com.mieker.santaApi.service;

import java.util.List;

import com.mieker.santaApi.model.Role;
import com.mieker.santaApi.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mieker.santaApi.exception.GiftError;
import com.mieker.santaApi.exception.GiftException;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, GiftRepository giftRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.giftRepository = giftRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        userFromDb.setUsername(user.getUsername());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setWantedGifts(user.getWantedGifts());
        userFromDb.setGiftsToGive(user.getGiftsToGive());

        return userRepository.save(userFromDb);
    }

    @Override
    public User patchUser(String userId, User user) {
        User userFromDb = userRepository.findByUserId(userId);
        if (!user.getUsername().isBlank() && user.getUsername() != null) {
            userFromDb.setUsername(user.getUsername());
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
    public User reserveGift(String userId, String giftId) {
        User user = getUserById(userId);
        Gift gift = getGiftById(giftId);
        user.addGiftToGiftsToGiveList(gift);
        
        return userRepository.save(user);
    }

    @Override
    public void removeFromReservedGift(String userId, String giftId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void giveUserRole(String userId) {
        User tempUser = userRepository.save(userRepository.findByUserId(userId));
        tempUser.setEnabled(true);
        tempUser.getAuthorities().add(roleRepository.findByRole(Role.ERole.ROLE_USER));
        userRepository.save(tempUser);
    }

    @Override
    public void giveAdminRole(String userId) {
        User tempUser = userRepository.save(userRepository.findByUserId(userId));
        tempUser.setEnabled(true);
        tempUser.getAuthorities().add(roleRepository.findByRole(Role.ERole.ROLE_ADMIN));
        userRepository.save(tempUser);
    }

    private Gift getGiftById(String giftId) {
        Gift gift = giftRepository.findByGiftId(giftId);
        if (gift == null) {
            throw new GiftException(GiftError.GIFT_NOT_EXIST);
        } else {
            return gift;
        }
    }
}
