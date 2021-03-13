package com.habr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habrahabr")
public class MainController {

    @GetMapping
    public String habr() {
        return "hi";
    }
}