package com.miti.server.controller.controllerHTML;

import com.miti.server.check.UserChecker;
import com.miti.server.entity.User;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserControllerHTML {
    private final UserService userService;
    UserChecker uc = new UserChecker();

    public UserControllerHTML(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userHTML")
    public String showAllUsers(Map<String, Object> model) {
        String message = "";
        List<User> users = userService.getAllUsers();
        model.put("users", users);
        model.put("message", message);

        return "user";
    }

    @PostMapping("/userHTML")
    public String addUser(@RequestParam String userName,
                          @RequestParam String password,
                          @RequestParam String role,
                          Map<String, Object> model) {
        String message = "";

        if (uc.userChecker(userName, password) && userService.getUserByUserName(userName) == null) {
            User user = new User(userName, password, role);
            userService.addUser(user);
        } else if (message.equals(""))
            message = "Empty field or login already exist";

        List<User> users = userService.getAllUsers();
        model.put("users", users);
        model.put("message", message);

        return "user";
    }
}
