package com.miti.server.api;

import com.miti.server.model.entity.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {

  User addUser(User user);

  void addAllUsers(List<User> users);

  User editUser(Long userId, User user);

  User getUserById(Long userId);

  User getUserByUsername(String username);

  User getUserByName(String name);

  User getUserByEmail(String email);

  List<User> getAllUsers();

  List<User> getUsersByRole(String roleName);

  List<User> getUsersByStatus(Boolean status);

  List<User> getUsersByLastAuthDateAfter(String date) throws ParseException;

  List<User> getUsersByRegistrationDateAfter(String date) throws ParseException;

  void deleteById(Long userId);
}
