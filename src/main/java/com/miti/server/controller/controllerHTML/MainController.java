package com.miti.server.controller.controllerHTML;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.net.www.http.HttpClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model, HttpServletRequest res, HttpServletRequest req) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page");
        String token = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token"))
                    token = "Bearer " + cookie.getValue().toString();
            }
        }

        model.addAttribute("token", token);
        model.addAttribute("yourToken", token);
        return "main";
    }
}
