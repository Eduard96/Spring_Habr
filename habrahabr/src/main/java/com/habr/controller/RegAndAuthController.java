package com.habr.controller;

import com.habr.dto.UserDTOToService;
import com.habr.micro_services.RegAndAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Last step
 */
@RestController
@RequestMapping("/edobr.com")
public class RegAndAuthController {

    private final RegAndAuthService regAndAuthService;

    @Autowired
    public RegAndAuthController(RegAndAuthService regAndAuthService) {
        this.regAndAuthService = regAndAuthService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDTOToService user) {
        return regAndAuthService.signup(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDTOToService user) {
        return regAndAuthService.signin(user);
    }



//    private final WebClient webClient;

//    @Autowired
//    public RegistrationAndAuthenticationController(WebClient webClient) {
//        this.webClient = webClient;
//    }



//    @PostMapping(value = "/signup")
//    public Mono<?> signup( @Valid @RequestBody RegAuthUserDTO user) {
//        return webClient.post()
//                .uri("/api/auth/signup")
//                .body(BodyInserters.fromValue(user))
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<String> signin(@RequestBody RegAuthUserDTO user) {
//        return webClient.post()
//                .uri("/api/auth/signin")
//                .body(BodyInserters.fromValue(user))
//                .retrieve()
//                .bodyToMono(String.class);
//    }
}
