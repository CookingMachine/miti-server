package com.miti.server.controller.controllerHTML;

import com.miti.server.model.dto.UserDTO;
import com.miti.server.model.entity.User;
import com.miti.server.enums.UserRole;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserControllerHTML {

    private final UserService userService;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
    public String userList(Model model, HttpServletRequest req, HttpServletResponse res) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "lists/userList";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public String showAddUserPage(Model model) {

        UserDTO userDTO = new UserDTO();
        model.addAttribute("userForm", userDTO);

        return "user";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.POST)
    public String saveUser(Model model, //
                           @ModelAttribute("userForm") UserDTO userDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        UserRole role = userDTO.getRole();
        String email = userDTO.getEmail();

        if (username != null && username.length() > 0
                && password != null && password.length() > 0) {
            userService.addUser(username, password, email, role);

            return "redirect:/userList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "user";
    }

}
