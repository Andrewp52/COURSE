package com.pashenko.eshop.controllers;

import com.pashenko.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/profile")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String showUserProfile(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }
}
