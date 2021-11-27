package com.mieker.santaApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mieker.santaApi.model.User;
import com.mieker.santaApi.repository.UserRepository;
import com.mieker.santaApi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
    
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User putUser(@PathVariable String userId, @RequestBody User user) {
        return userService.putUser(userId, user);
    }
    
    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable String userId, @RequestBody User user) {
        return userService.putUser(userId, user);
    }
    
    @PostMapping("/{userId}/{giftId}")
    public User addWantedGiftToUser(@PathVariable String userId, @PathVariable String giftId) {
        return userService.addWantedGift(userId, giftId);
    }
    
    @DeleteMapping("/{userId}/{giftId}")
    public User removeWantedGiftFromUser(@PathVariable String userId, @PathVariable String giftId) {
        return userService.removeFromWantedGift(userId, giftId);
    }
}
