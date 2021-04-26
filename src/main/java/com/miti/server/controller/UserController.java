package com.miti.server.controller;

import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.model.entity.User;
import com.miti.server.api.UserService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final UserService userService;
  //private final UserDetailsService userDetailsService;
  //private final JwtUtil util;

  @PostMapping(value = "")
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @PatchMapping(value = "")
  public User editUser(@RequestBody User user) {
    return userService.editUser(user);
  }

  @GetMapping(value = "/{id}")
  public User getUserById(@PathVariable Long id) {
  //    if (Check.role(req, userDetailsService, util, "ADMINISTRATION") ||
  //        Check.role(req, userDetailsService, util, "MODERATION")) {
    return userService.getUserById(id);
  }
  //    throw new RuntimeException("No permission!");
  //  }

  @GetMapping(value = "/getByUsername/{username}")
  public User getUserByUserName(@PathVariable String username) {
    return userService.getUserByUsername(username);
  }

  @GetMapping(value = "/getByName/{name}")
  public User getUserByName(@PathVariable String name) {
    return userService.getUserByName(name);
  }

  @GetMapping(value = "/getByEmail/{email}")
  public User getUserByEmail(@PathVariable String email) {
    return userService.getUserByEmail(email);
  }

  @GetMapping(value = "")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping(value = "/getByStatus/{status}")
  public List<User> getUsersByStatus(@PathVariable boolean status) {
    return userService.getUsersByStatus(status);
  }

  @GetMapping(value = "/getByRole/{role}")
  public List<User> getUsersByRole(@PathVariable String role) {
    return userService.getUsersByRole(role);
  }

  @GetMapping(value = "/getByLastAuthDate/{lastAuthDate}")
  public List<User> getUsersByLastAuthDateAfter(@PathVariable String lastAuthDate)
      throws ParseException {
    return userService.getUsersByLastAuthDateAfter(lastAuthDate);
  }

  @GetMapping(value = "/getByRegistrationDate/{registrationDate}")
  public List<User> getUsersByRegistrationDateAfter(@PathVariable String registrationDate)
      throws ParseException {
    return userService.getUsersByRegistrationDateAfter(registrationDate);
  }

  @DeleteMapping(value = "/{id}")
  public String deleteUserById(@PathVariable Long id) {
    userService.deleteById(id);
    return "Successfully removed USER with id [" + id + "]";
  }
}
