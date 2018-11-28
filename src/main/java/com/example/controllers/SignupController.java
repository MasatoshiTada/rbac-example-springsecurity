package com.example.controllers;

import com.example.models.User;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kawasima
 * @author tada
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserService userService;

    @GetMapping("/signup")
    public String index() {
        return "signup/index";
    }

    @PostMapping("/signup")
    public String signup(User user, @RequestParam("role") String roleName) {
        userService.register(user, roleName);
        return "redirect:/login";
    }
}
