package com.miti.server.service.impl;

import com.miti.server.model.entity.User;
import com.miti.server.model.dto.UserDTO;
import com.miti.server.enums.UserRole;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.UserService;
import org.springframework.context.annotation.Bean;
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
    public User addUser(String username, String password, String email, UserRole role) {
        User _user = new User(new UserDTO(username, password, email, role));

        return addUser(_user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User with id: " + userId + " doesn't exist"));
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUsername(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.getUsersByRole(role);
    }
}
