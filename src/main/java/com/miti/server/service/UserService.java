package com.miti.server.service;

import com.miti.server.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getUserByUserName(String userName);

    User getUserById(Long userId);

    List<User> getAllUsers();

    List<User> getUsersByRole(String role);
}
