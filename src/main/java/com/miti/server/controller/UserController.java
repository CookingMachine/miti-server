package com.miti.server.controller;

import com.miti.server.model.entity.User;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private final UserService userService;

  @PostMapping(value = "/addUser")
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @PutMapping(value = "/editUser")
  public User editUser(@RequestBody User user, @RequestParam Long userId) {
    return userService.editUser(userId, user);
  }

  @GetMapping(value = "/getUserById")
  public User getUserById(@RequestParam Long userId) {
    return userService.getUserById(userId);
  }

  @GetMapping(value = "/getUserByUsername")
  public User getUserByUserName(@RequestParam String username) {
    return userService.getUserByUsername(username);
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
