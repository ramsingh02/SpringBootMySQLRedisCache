package com.mysql.redis.controller;

import com.mysql.redis.model.User;
import com.mysql.redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final static Logger LOGGER =
            LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Cacheable("users")
    public List<User> getAllUsers(){
        LOGGER.info("User fetching from database :::::: getAllUsers");
        return userService.getAllUsers();
    }

    @GetMapping("{userId}")
    @Cacheable(value = "users", key = "#userId")
    public User getUserById(@PathVariable("userId") Long userId){
        LOGGER.info("User fetching from database :::::: getUserById : "+userId);
        return userService.getUserById(userId);
    }

    @PutMapping("{userId}")
    @CachePut(value = "users", key = "#userId")
    public User updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody User user
    ){
        LOGGER.info("User fetching from database :::::: updateUser : "+userId);
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("{userId}")
    @CacheEvict(value = "users", key = "#userId")
    public String deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return "User successfully deleted :::: "+userId;
    }
}
