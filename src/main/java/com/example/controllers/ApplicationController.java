package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kawasima
 * @author tada
 */
@Controller
public class ApplicationController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
