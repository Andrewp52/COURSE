package com.pashenko.SpringBootHW.controller;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }

    @GetMapping("/accessDenied")
    @ResponseBody
    public String showAccessDeniedPage() {
        return "access denied";
    }
}
