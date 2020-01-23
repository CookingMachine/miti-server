package com.miti.server.controller;

import com.miti.server.check.UserChecker;
import com.miti.server.entity.User;
import com.miti.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    private UserChecker uc = new UserChecker();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUserByUserName(@RequestParam String userName) {
        if (userService.getUserByUserName(userName) != null)
            return userService.getUserByUserName(userName);
        else
            return null;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByRole")
    public List<User> getUserByRole(@RequestParam String role) {
        return userService.getUsersByRole(role);
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        if  (uc.userChecker(user.getUserName(), user.getPassword()) &&
                userService.getUserByUserName(user.getUserName()) == null) {

            return userService.addUser(user);
        } else
            return null;
    }
}
