package com.miti.server.controller.controllerRest;

import com.miti.server.check.UserChecker;
import com.miti.server.model.dto.UserDTO;
import com.miti.server.model.entity.User;
import com.miti.server.enums.UserRole;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    private UserChecker uc = new UserChecker();

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUserByUserName(@RequestParam String userName) {
        if (userService.getUserByUserName(userName) != null)
            return userService.getUserByUserName(userName);
        else
            return null;
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByRole")
    public List<User> getUserByRole(@RequestParam UserRole role) {
        return userService.getUsersByRole(role);
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
