package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.User;
import com.miti.server.enums.UserRole;
import com.miti.server.model.form.UserForm;
import com.miti.server.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserControllerHTML {
    private UserService userService;

    public UserControllerHTML(UserService userService) {
        this.userService = userService;
    }

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "lists/userList";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public String showAddUserPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "user";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.POST)
    public String saveUser(Model model, //
                             @ModelAttribute("userForm") UserForm userForm) {

        String username = userForm.getUserName();
        String password = userForm.getPassword();
        UserRole role = UserRole.valueOf(userForm.getRole());
        String email = userForm.getEmail();

        if (username != null && username.length() > 0
                && password != null && password.length() > 0) {
            userService.addUser(username, password, email, role);

            return "redirect:/userList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "user";
    }

}
