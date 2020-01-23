package com.miti.server.service.impl;

import com.miti.server.entity.User;
import com.miti.server.repo.UserRepo;
import com.miti.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser(User user) {
        User newUser = new User(user.getUserName(),
                user.getPassword(),
                user.getRole());

        return userRepo.save(newUser);
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
