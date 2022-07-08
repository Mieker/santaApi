package com.mieker.santaApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mieker.santaApi.model.User;
import com.mieker.santaApi.repository.UserRepository;
import com.mieker.santaApi.service.UserService;

import javax.annotation.security.RolesAllowed;

@RestController
@CrossOrigin(origins = "https://santa-api-app.herokuapp.com/user/hello", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello man!";
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RolesAllowed({"ROLE_USER"})        // user can only register new users
    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("/{userId}")
    public User putUser(@PathVariable String userId, @RequestBody User user) {
        return userService.putUser(userId, user);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable String userId, @RequestBody User user) {
        return userService.putUser(userId, user);
    }

    @PostMapping("/{userId}/wish/{giftId}")
    public User addWantedGiftToUser(@PathVariable String userId, @PathVariable String giftId) {
        return userService.addWantedGift(userId, giftId);
    }

    @DeleteMapping("/{userId}/wish/{giftId}")
    public User removeWantedGiftFromUser(@PathVariable String userId, @PathVariable String giftId) {
        return userService.removeFromWantedGift(userId, giftId);
    }

    @PostMapping("/{userId}/gives/{giftId}")
    public User addGiftToReservedGiftsList(@PathVariable String userId, @PathVariable String giftId) {
        return userService.reserveGift(userId, giftId);
    }

    @PutMapping("/{userId}/user_role")
    public User giveUserRole(@PathVariable String userId) {
        userService.giveUserRole(userId);
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}/admin_role")
    public User giveAdminRole(@PathVariable String userId) {
        userService.giveAdminRole(userId);
        return userService.getUserById(userId);
    }
}
