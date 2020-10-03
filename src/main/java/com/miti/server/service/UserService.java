package com.miti.server.service;

import com.miti.server.model.dto.UserDTO;
import com.miti.server.model.entity.User;
import com.miti.server.enums.UserRole;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User addUser(String userName, String password, String email, UserRole role);

    User addUser(UserDTO userDTO);

    User getUserByUserName(String userName);

    User getUserById(Long userId);

    List<User> getAllUsers();

    List<User> getUsersByRole(UserRole role);
}
