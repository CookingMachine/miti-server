package com.miti.server.controller;

import com.miti.server.api.UserService;
import com.miti.server.model.entity.User;
import com.miti.server.model.jwt.JwtRequest;
import com.miti.server.model.request.UserRequest;
import com.miti.server.model.response.UserResponse;
import com.miti.server.util.Authenticate;
import java.text.ParseException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final UserService userService;
  private final Authenticate authenticate;

  @PostMapping(value = "")
  public UserResponse addUser(@RequestBody UserRequest user) throws Exception {
    UserResponse userResponse = userService.addUser(user);
    userResponse.setJwtToken(
        authenticate.generateJwtToken(new JwtRequest(user.getUsername(), user.getPassword())));

    return userResponse;
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
