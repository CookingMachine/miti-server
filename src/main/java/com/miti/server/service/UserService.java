package com.miti.server.service;

import com.miti.data.model.User;
import com.miti.server.api.request.UserRequest;
import com.miti.server.api.response.UserResponse;
import java.text.ParseException;
import java.util.List;

public interface UserService {

  UserResponse addUser(UserRequest userRequest);

  void addAllUsers(List<User> users);

  User editUser(User user);

  User getUserById(Long userId);

  UserResponse getUserByToken(String token);

  User getUserByUsername(String username);

  User getUserByName(String name);

  User getUserByEmail(String email);

  List<User> getAllUsers();

  List<User> getUsersByRole(String roleName);

  List<User> getUsersByStatus(Boolean status);

  List<User> getUsersByLastAuthDateAfter(String date) throws ParseException;

  List<User> getUsersByRegistrationDateAfter(String date) throws ParseException;

  void addFavouriteRecipe(Long userId, Long recipeId);

  void deleteFavouriteRecipe(Long userId, Long recipeId);

  void deleteById(Long userId);
}
