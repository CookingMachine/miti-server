package com.miti.server.controller.controllerHTML;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerHTML {
    @GetMapping
    public String mainPage() {
        return "main";
    }
}
