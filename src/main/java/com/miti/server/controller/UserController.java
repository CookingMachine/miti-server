package com.miti.server.controller;

import com.miti.data.model.User;
import com.miti.server.api.request.UserRequest;
import com.miti.server.api.response.UserResponse;
import com.miti.server.service.UserService;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.jwt.JwtRequest;
import com.miti.server.util.Authenticate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
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

  @GetMapping(value = "/getByToken")
  public UserResponse getUserByJWTToken(@RequestHeader(name = "Authorization") String token) {
    return userService.getUserByToken(token.substring(7));
  }

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

  @PostMapping(value = "/addFavouriteRecipe")
  public ResponseEntity<?> addFavouriteRecipe(@RequestParam Long userId, @RequestParam Long recipeId) {
    try {
      userService.addFavouriteRecipe(userId, recipeId);
      return new ResponseEntity<>("Recipe [" + recipeId + "] has been added to favourite list of user [" + userId + "]!", HttpStatus.OK);
    } catch (RuntimeException re) {
      return new ResponseEntity<>("Recipe [" + recipeId + "] already exists in favourite list of user [" + userId + "]!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(value = "/deleteFavouriteRecipe")
  public ResponseEntity<?> deleteFavouriteRecipe(@RequestParam Long userId, @RequestParam Long recipeId) {
    try {
      userService.deleteFavouriteRecipe(userId, recipeId);
      return new ResponseEntity<>("Recipe [" + recipeId + "] has been deleted from favourite list of user [" + userId + "]!", HttpStatus.OK);
    } catch (RuntimeException re) {
      return new ResponseEntity<>("Recipe [" + recipeId + "] doesn't exist in favourite list of user [" + userId + "]!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(value = "/{id}")
  public String deleteUserById(@PathVariable Long id) {
    userService.deleteById(id);

    return "Successfully removed USER with id [" + id + "]";
  }

  private boolean checkRole(HttpServletRequest req,
                                  UserDetailsService userDetailsService,
                                  JwtUtil util,
                                  String role) {
    String token = req.getHeader("Authorization").substring(7);
    UserDetails details = userDetailsService.loadUserByUsername(util.getUsernameFromToken(token));
    return details != null && details.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals(role));
  }
}
