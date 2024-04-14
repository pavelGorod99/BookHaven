package com.example.eLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = {"/"})
    public String displayHomePage() {
        return "home/index";
    }
}
