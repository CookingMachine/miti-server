package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LoginHTML {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String ingredientList(Model model) {
        return "login";
    }

}
