package com.mysql.redis.service.impl;

import com.mysql.redis.exception.ResourcrNotFoundException;
import com.mysql.redis.model.User;
import com.mysql.redis.repository.UserRepository;
import com.mysql.redis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourcrNotFoundException("User not found ::: "+userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long userId, User user) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcrNotFoundException("User not found ::: "+userId));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourcrNotFoundException("User not found ::: "+userId));
        userRepository.delete(existingUser);
    }
}
