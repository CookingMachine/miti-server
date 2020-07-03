package com.miti.server.service.impl;

import com.miti.server.entity.User;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) { return userRepository.save(user); }

    @Override
    public User addUser(String userName, String password, String role) {
        User _user = new User(userName, password, role);

        return addUser(_user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User with id: " + userId + " doesn't exist"));
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users = userRepository.getUsersByRole(role);
        return users;
    }
}
