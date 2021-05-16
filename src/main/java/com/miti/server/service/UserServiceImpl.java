package com.miti.server.service;

import com.miti.server.api.UserService;
import com.miti.server.mapper.UserMapper;
import com.miti.server.model.entity.User;
import com.miti.server.model.enums.Role;
import com.miti.server.model.request.UserRequest;
import com.miti.server.model.response.UserResponse;
import com.miti.server.repository.CommentRepository;
import com.miti.server.repository.ContextIngredientRepository;
import com.miti.server.repository.RatingRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.repository.UserRepository;
import com.miti.server.util.Check;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final ContextIngredientRepository contextIngredientRepository;
  private final RatingRepository ratingRepository;

  private final PasswordEncoder passwordEncoder;
  private final UserMapper mapper;

  @Override
  public UserResponse addUser(UserRequest userRequest) {
    User user = mapper.userRequestToUserModel(userRequest);
    userRepository.save(user);

    return mapper.userModelToUserResponse(user);
  }

  @Override
  public void addAllUsers(List<User> users) {
    List<User> _users = new ArrayList<>();
    for (User user : users) {
      if (existsByUsernameAndEmail(user.getUsername(), user.getEmail())) {
        _users.add(user);
      }
    }
    userRepository.saveAll(_users);
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
  public User getUserByUsername(String username) {
    if (Check.param(username)) {
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
    if (Check.param(name)) {
      return userRepository.getUserByName(name);
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public User getUserByEmail(String email) {
    if (Check.param(email)) {
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
    if (Check.param(roleName)) {
      Role role = Role.valueOf(roleName);
      List<User> users = userRepository.getUsersByRole(role);
      if (users != null) {
        return users;
      }
      throw new RuntimeException("Users with role: " + roleName + " don't exist!");
    }
    throw new RuntimeException("RoleName: " + roleName + " is incorrect!");

  }

  @Override
  public List<User> getUsersByStatus(Boolean status) {
    if (Check.param(status)) {
      List<User> users = userRepository.getUsersByStatus(status);
      if (users != null) {
        return users;
      }
      throw new RuntimeException("Users with status: " + status + " don't exist!");
    }
    throw new RuntimeException("Status: " + status + " is incorrect!");
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
