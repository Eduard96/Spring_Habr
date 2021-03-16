package com.habr.controller;

import com.habr.model.User;
import com.habr.services.RegistrationAndAuthorisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/habrahabr")
public class RegistrationAndAuthenticationController {

    private final RegistrationAndAuthorisationService regAndAuthService;

    @Autowired
    public RegistrationAndAuthenticationController(RegistrationAndAuthorisationService regAndAuthService) {
        this.regAndAuthService = regAndAuthService;
    }

    @GetMapping
    public String mainPage() {
        return "habrahabr";
    }

    @GetMapping("/registration")
    public String returnRegPage(Model model) {
        model.addAttribute("user", new User());
        return "registration_and_authentication/reg_page";
    }

    @GetMapping("/sign_in")
    public String singIn(Model model) {
        model.addAttribute("user", new User());
        return "registration_and_authentication/sign_in";
    }

//    @GetMapping("/authorisation")
//    public String authorisation() {
//        return "true";
//    }

    @PostMapping("/reg_success")
    public String reg(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "registration_and_authentication/reg_page";
        regAndAuthService.persist(user);
        return "redirect:/habrahabr";
    }
}
