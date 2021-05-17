package com.miti.server.service.impl;

import com.miti.data.enums.Role;
import com.miti.data.mapper.UserMapper;
import com.miti.data.model.User;
import com.miti.data.repository.*;
import com.miti.server.api.request.UserRequest;
import com.miti.server.api.response.UserResponse;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final ContextIngredientRepository contextIngredientRepository;
  private final RatingRepository ratingRepository;

  private final JwtUtil jwtUtil;

  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  @Override
  public UserResponse addUser(UserRequest userRequest) {
    User user = userMapper.userRequestToUserModel(userRequest);
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

    userRepository.save(user);

    return userMapper.userModelToUserResponse(user);
  }

  @Override
  public void addAllUsers(List<User> users) {
    userRepository.saveAll(
        users.stream()
            .filter(user -> existsByUsernameAndEmail(user.getUsername(), user.getEmail()))
            .collect(Collectors.toList())
    );
  }

  @Override
  public User editUser(User user) {
    return userRepository.findById(user.getId()).map(newUser -> {
      newUser.setName(user.getName());
      newUser.setPassword(passwordEncoder.encode(user.getPassword()));
      newUser.setEmail(user.getEmail());
      newUser.setRole(user.getRole());
      return userRepository.save(newUser);
    }).orElseThrow(()
        -> new RuntimeException("User with id: " + user.getId() + " doesn't exist!"));
  }

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElseThrow(()
        -> new RuntimeException("User with id: " + userId + " doesn't exist!"));
  }

  @Override
  public UserResponse getUserByToken(String token) {
    UserResponse userResponse = userMapper.userModelToUserResponse(
        getUserByUsername(jwtUtil.getUsernameFromToken(token)));
    userResponse.setJwtToken(token);

    return userResponse;
  }

  @Override
  public User getUserByUsername(String username) {
    if (!StringUtils.isEmpty(username)) {
      User user = userRepository.getUserByUsername(username);
      if (user != null) {
        return userRepository.getUserByUsername(username);
      }
      throw new RuntimeException("User with username: " + username + " doesn't exist!");
    }
    throw new RuntimeException("Username: " + username + " is incorrect!");
  }

  @Override
  public User getUserByName(String name) {
    if (!StringUtils.isEmpty(name)) {
      return userRepository.getUserByName(name);
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public User getUserByEmail(String email) {
    if (!StringUtils.isEmpty(email)) {
      User user = userRepository.getUserByEmail(email);
      if (user != null) {
        return user;
      }
      throw new RuntimeException("User with e-mail: " + email + " doesn't exist!");
    }
    throw new RuntimeException("Email: " + email + " is incorrect!");
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getUsersByRole(String roleName) {
    if (!StringUtils.isEmpty(roleName)) {
      Role role = Role.valueOf(roleName);
      List<User> users = userRepository.getUsersByRole(role);
      if (users != null) {
        return users;
      }
      throw new RuntimeException("Users with role: " + roleName + " don't exist!");
    } else {
      throw new RuntimeException("RoleName: " + roleName + " is incorrect!");
    }
  }

  @Override
  public List<User> getUsersByStatus(Boolean status) {
    if (!StringUtils.isEmpty(status)) {
      List<User> users = userRepository.getUsersByStatus(status);
      if (users != null) {
        return users;
      }
      throw new RuntimeException("Users with status: " + status + " don't exist!");
    } else {
      throw new RuntimeException("Status: " + status + " is incorrect!");
    }
  }

  @Override
  public List<User> getUsersByLastAuthDateAfter(String date) throws ParseException {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date _date = format.parse(date);
    return userRepository.getUsersByLastAuthDateAfter(_date);
  }

  @Override
  public List<User> getUsersByRegistrationDateAfter(String date) throws ParseException {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date _date = format.parse(date);
    return userRepository.getUsersByRegistrationDateAfter(_date);
  }

  @Override
  public void addFavouriteRecipe(Long userId, Long recipeId) {
    if (userRepository.getUserByFavouriteRecipe(userId, recipeId) == null)
      userRepository.addFavouriteRecipe(userId, recipeId);
    else {
      throw new RuntimeException("");
    }
  }

  @Override
  public void deleteFavouriteRecipe(Long userId, Long recipeId) {
    if (userRepository.getUserByFavouriteRecipe(userId,recipeId) != null)
      userRepository.deleteFavouriteRecipe(userId, recipeId);
    else {
      throw new RuntimeException("");
    }
  }

  @Override
  public void deleteById(Long userId) {
    User user = getUserById(userId);

    user.getRecipeList().forEach(recipe -> {

      recipe.getContextIngredientList().forEach(contextIngredient ->
          contextIngredientRepository.deleteById(contextIngredient.getId()));

      recipe.getCommentList().forEach(comment ->
          commentRepository.deleteById(comment.getId()));

      recipe.getRating().forEach(rating ->
          ratingRepository.deleteById(rating.getId()));

      recipeRepository.deleteById(recipe.getId());
    });

    user.getRating().forEach(rating ->
        ratingRepository.deleteById(rating.getId()));

    user.getCommentList().forEach(comment ->
        commentRepository.deleteById(comment.getId()));

    userRepository.deleteById(userId);
  }

  public boolean existsByUsernameAndEmail(String username, String email) {
    return !userRepository.existsByUsername(username) && !userRepository.existsByEmail(email);
  }
}
