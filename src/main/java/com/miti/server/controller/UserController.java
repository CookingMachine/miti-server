package com.miti.server.controller;

import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.model.entity.User;
import com.miti.server.service.UserService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

  @PostMapping(value = "/user/addUser")
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @PutMapping(value = "/user/editUser")
  public User editUser(@RequestParam(name = "id") Long userId, @RequestBody User user) {
    return userService.editUser(userId, user);
  }

  @GetMapping(value = "/user/getUserById")
  public User getUserById(@RequestParam(name = "id") Long userId, HttpServletRequest req)
  {
    if (Check.role(req, userDetailsService, util, "ADMINISTRATION") ||
        Check.role(req, userDetailsService, util, "MODERATION"))
      return userService.getUserById(userId);
    throw new RuntimeException("No permission!");
  }

  @GetMapping(value = "/user/getUserByUsername")
  public User getUserByUserName(@RequestParam(name = "login") String username) {
    return userService.getUserByUsername(username);
  }

  @GetMapping(value = "/user/getUserByName")
  public User getUserByName(@RequestParam(name = "name") String name) {
    return userService.getUserByName(name);
  }

  @GetMapping(value = "/user/getUserByEmail")
  public User getUserByEmail(@RequestParam(name = "email") String email) {
    return userService.getUserByEmail(email);
  }

  @GetMapping(value = "/user/getAllUsers")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping(value = "/user/getUsersByStatus")
  public List<User> getUsersByStatus(@RequestParam(name = "status", defaultValue = "true") boolean status) {
    return userService.getUsersByStatus(status);
  }

  @GetMapping(value = "/user/getUsersByRole")
  public List<User> getUsersByRole(@RequestParam(name = "role") String roleName) {
    return userService.getUsersByRole(roleName);
  }

  @GetMapping(value = "/user/getUsersByLastAuthDateAfter")
  public List<User> getUsersByLastAuthDateAfter(@RequestParam(name = "date") String date) throws ParseException {
    return userService.getUsersByLastAuthDateAfter(date);
  }

  @GetMapping(value = "/user/getUsersByRegistrationDateAfter")
  public List<User> getUsersByRegistrationDateAfter(@RequestParam(name = "date") String date) throws ParseException {
    return userService.getUsersByRegistrationDateAfter(date);
  }

  @DeleteMapping(value = "/user/deleteUserById")
  public String deleteUserById(@RequestParam(name = "id") Long userId) {
    userService.deleteById(userId);
    return "Done!";
  }
}
