package com.miti.server.controller;

import com.miti.server.check.UserChecker;
import com.miti.server.entity.User;
import com.miti.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user")
    public String showAllUsers(Map<String, Object> model) {
        String message = "";
        List<User> users = userRepo.findAll();
        model.put("users", users);
        model.put("message", message);

        return "user";
    }

    @PostMapping("/user")
    public String addUser(@RequestParam String userName,
                          @RequestParam String password,
                          @RequestParam String role,
                          Map<String, Object> model) {
        UserChecker uc = new UserChecker();
        String message = "";

        if (uc.userChecker(userName, password) && userRepo.getUserByUserName(userName) == null) {
            User user = new User(userName, password, role);
            userRepo.save(user);
        } else if (message.equals(""))
            message = "Empty field or login already exist";

        List<User> users = userRepo.findAll();
        model.put("users", users);
        model.put("message", message);

        return "user";
    }
}
