package com.miti.server.service;

import com.miti.server.model.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void addAllUsers(List<User> users);

    User getUserById(Long userId);
    User getUserByUsername(String username);
    User getUserByEmail(String email);

    List<User> getAllUsers();
    List<User> getUsersByRole(String roleName);
    List<User> getUsersByStatus(Boolean status);

    void deleteById(Long userId);
}
