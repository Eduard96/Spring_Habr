package com.habr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Last step
 */
@Controller
@RequestMapping("/habrahabr")
public class RegistrationAndAuthenticationController {

//    private final RegistrationAndAuthorisationService regAndAuthService;
//
//    @Autowired
//    public RegistrationAndAuthenticationController(RegistrationAndAuthorisationService regAndAuthService) {
//        this.regAndAuthService = regAndAuthService;
//    }
//
//    @GetMapping
//    public String mainPage() {
//        return "habrahabr";
//    }
//
//    @GetMapping("/registration")
//    public String returnRegPage(Model model) {
//        model.addAttribute("user", new User());
//        return "registration_and_authentication/reg_page";
//    }
//
//    @GetMapping("/sign_in")
//    public String singIn(Model model) {
//        model.addAttribute("user", new User());
//        return "registration_and_authentication/sign_in";
//    }
//
////    @GetMapping("/authorisation")
////    public String authorisation() {
////        return "true";
////    }
//
//    @PostMapping(value = "/reg_success")
//    @ResponseBody
//    public User reg( @RequestBody User user) {
//        //if(bindingResult.hasErrors()) return "registration_and_authentication/reg_page";
//        //regAndAuthService.persist(user);
//        return user;
//    }
}
