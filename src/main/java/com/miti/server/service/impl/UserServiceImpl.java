package com.miti.server.service.impl;

import com.miti.server.enums.Role;
import com.miti.server.model.entity.Comment;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.repository.CommentRepository;
import com.miti.server.repository.ContextIngredientRepository;
import com.miti.server.repository.RecipeRepository;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.UserService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RecipeRepository recipeRepository;
  private final CommentRepository commentRepository;
  private final ContextIngredientRepository contextIngredientRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User addUser(User user) {
    if (existsByUsernameAndEmail(user.getUsername(), user.getEmail()))
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
    for (User user : users) {
      if (existsByUsernameAndEmail(user.getUsername(), user.getEmail()))
        _users.add(user);
    }
    userRepository.saveAll(_users);
  }

  @Override
  public User editUser(Long userId, User newUser) {
    return userRepository.findById(userId).map(user -> {
          user.setPassword(passwordEncoder.encode(newUser.getPassword()));
          user.setEmail(newUser.getEmail());
          user.setRole(newUser.getRole());
          return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User with id: " + userId + " doesn't exist!"));
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
    return userRepository.findAll();
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
    List<Recipe> recipes = user.getRecipeList();
    for (Recipe recipe : recipes) {
      List<ContextIngredient> contextIngredients = recipe.getContextIngredientList();
      List<Comment> comments = recipe.getCommentList();
      for (ContextIngredient contextIngredient : contextIngredients)
        deleteIngredientContextById(contextIngredient.getId());
      for (Comment comment : comments)
        deleteCommentById(comment.getId());
      deleteRecipeById(recipe.getId());
    }

    List<Comment> comments = user.getCommentList();
    for (Comment comment : comments)
      deleteCommentById(comment.getId());
    userRepository.deleteById(userId);
  }

  @Override
  public void deleteRecipeById(Long recipeId) {
    recipeRepository.deleteById(recipeId);
  }

  @Override
  public void deleteCommentById(Long commentId) {
    commentRepository.deleteById(commentId);
  }

  @Override
  public void deleteIngredientContextById(Long id) {
    contextIngredientRepository.deleteById(id);
  }

  public boolean existsByUsernameAndEmail(String username, String email) {
    if (userRepository.existsByUsername(username))
      return false;
    return !userRepository.existsByEmail(email);
  }
}
