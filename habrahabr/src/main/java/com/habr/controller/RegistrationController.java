package com.habr.controller;

import com.habr.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/habrahabr/registration")
public class RegistrationController {

    @GetMapping
    public String returnRegPage(Model model) {
        model.addAttribute("user", new User());
        return "registration/reg_page";
    }
}
