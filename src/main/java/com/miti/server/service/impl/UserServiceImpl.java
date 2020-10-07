package com.miti.server.service.impl;

import com.miti.server.model.entity.User;
import com.miti.server.model.dto.UserDTO;
import com.miti.server.enums.UserRole;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) { return userRepository.save(user); }

    @Override
    public User addUser(String username, String password, String email, UserRole role) {
        User _user = new User(new UserDTO(username, passwordEncoder.encode(password), email, role));

        return addUser(_user);
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User _user = new User();
        _user.setUsername(userDTO.getUsername());
        _user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        _user.setEmail(userDTO.getEmail());
        _user.setRole(userDTO.getRole());
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
