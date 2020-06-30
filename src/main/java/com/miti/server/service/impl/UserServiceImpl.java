package com.miti.server.service.impl;

import com.miti.server.entity.User;
import com.miti.server.repo.UserRepo;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user) { return userRepo.save(user); }

    @Override
    public User addUser(String userName, String password, String role) {
        User _user = new User(userName, password, role);

        return addUser(_user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(()
                -> new RuntimeException("User with id: " + userId + " doesn't exist"));
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepo.getUserByUserName(userName);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users = userRepo.getUsersByRole(role);
        return users;
    }
}
