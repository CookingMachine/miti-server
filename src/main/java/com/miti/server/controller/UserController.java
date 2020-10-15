package com.miti.server.controller;

import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.model.entity.User;
import com.miti.server.service.UserService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private final UserService userService;
  private final UserDetailsService userDetailsService;
  private final JwtUtil util;

  @PostMapping(value = "/addUser")
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @PutMapping(value = "/editUser")
  public User editUser(@RequestParam Long userId, @RequestBody User user) {
    return userService.editUser(userId, user);
  }

  @GetMapping(value = "/getUserById")
  public User getUserById(@RequestParam Long userId, HttpServletRequest req)
  {
    if (Check.role(req, userDetailsService, util, "ADMINISTRATION") ||
        Check.role(req, userDetailsService, util, "MODERATION"))
      return userService.getUserById(userId);
    throw new RuntimeException("No permission!");
  }

  @GetMapping(value = "/getUserByUsername")
  public User getUserByUserName(@RequestParam String username) {
    return userService.getUserByUsername(username);
  }

  @GetMapping(value = "/getUserByName")
  public User getUserByName(@RequestParam String name) {
    return userService.getUserByName(name);
  }

  @GetMapping(value = "/getUserByEmail")
  public User getUserByEmail(@RequestParam String email) {
    return userService.getUserByEmail(email);
  }

  @GetMapping(value = "/getAllUsers")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping(value = "/getUsersByStatus")
  public List<User> getUsersByStatus(@RequestParam boolean status) {
    return userService.getUsersByStatus(status);
  }

  @GetMapping(value = "/getUsersByRole")
  public List<User> getUsersByRole(@RequestParam String roleName) {
    return userService.getUsersByRole(roleName);
  }

  @GetMapping(value = "/getUsersByLastAuthDateAfter")
  public List<User> getUsersByLastAuthDateAfter(@RequestParam String date) throws ParseException {
    return userService.getUsersByLastAuthDateAfter(date);
  }

  @GetMapping(value = "/getUsersByRegistrationDateAfter")
  public List<User> getUsersByRegistrationDateAfter(@RequestParam String date) throws ParseException {
    return userService.getUsersByRegistrationDateAfter(date);
  }

  @DeleteMapping(value = "/deleteUserById")
  public String deleteUserById(@RequestParam Long userId) {
    userService.deleteById(userId);
    return "Done!";
  }
}
