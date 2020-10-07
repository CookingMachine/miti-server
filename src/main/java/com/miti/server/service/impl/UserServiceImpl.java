package com.miti.server.service.impl;

import com.miti.server.enums.Role;
import com.miti.server.model.entity.User;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.UserService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        if (checkFields(user.getUsername(), user.getEmail()))
            return userRepository.save(new User(
                    user.getUsername(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getEmail(),
                    user.getRole()
            ));
        throw new RuntimeException("User with username: " + user.getUsername()
                + " or email: " + user.getEmail() + " already exists!");
    }

    @Override
    public void addAllUsers(List<User> users) {
        List<User> _users = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (checkFields(users.get(i).getUsername(), users.get(i).getEmail()))
                _users.add(users.get(i));
        }
        userRepository.saveAll(_users);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User with id: " + userId + " doesn't exist!"));
    }

    @Override
    public User getUserByUsername(String username) {
        if (Check.param(username)) {
            User user = userRepository.getUserByUsername(username);
            if (user != null)
                return userRepository.getUserByUsername(username);
            throw new RuntimeException("User with username: " + username + " doesn't exist!");
        }
        throw new RuntimeException("Username: " + username + " is incorrect!");
    }

    @Override
    public User getUserByEmail(String email) {
        if (Check.param(email)) {
            User user = userRepository.getUserByEmail(email);
            if (user != null)
                return user;
            throw new RuntimeException("User with e-mail: " + email + " doesn't exist!");
        }
        throw new RuntimeException("Email: " + email + " is incorrect!");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users != null)
            return users;
        throw new RuntimeException("Table is empty!\nUsers don't exist!");
    }

    @Override
    public List<User> getUsersByRole(String roleName) {
        if (Check.param(roleName)) {
            Role role = Role.valueOf(roleName);
            List<User> users = userRepository.getUsersByRole(role);
            if (users != null)
                return users;
            throw new RuntimeException("Users with role: " + roleName + " don't exist!");
        }
        throw new RuntimeException("RoleName: " + roleName + " is incorrect!");

    }

    @Override
    public List<User> getUsersByStatus(Boolean status) {
        if (Check.param(status)) {
            List<User> users = userRepository.getUsersByStatus(status);
            if (users != null)
                return users;
            throw new RuntimeException("Users with status: " + status + " don't exist!");
        }
        throw new RuntimeException("Status: " + status + " is incorrect!");
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean checkFields(String username, String email) {
        if (userRepository.existsByUsername(username))
            return false;
        if (userRepository.existsByEmail(email))
            return false;
        return true;
    }
}
