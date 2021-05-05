package com.habr.controller;

import com.habr.dto.RegAuthUserDTO;
import com.habr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Last step
 */
@RestController
@RequestMapping("/edobr.com")
public class RegistrationAndAuthenticationController {

    @Autowired
    public RestTemplate restTemplate;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegAuthUserDTO user) {
        ResponseEntity<Object> registeredUser = restTemplate.exchange("http://localhost:8082/api/auth/signup", HttpMethod.POST, new HttpEntity<>(user), Object.class);
        return ResponseEntity.ok(Objects.requireNonNull(registeredUser.getBody()));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestHeader RegAuthUserDTO headers) {
        ResponseEntity<Object> regUser = restTemplate.exchange("http://localhost:8082/api/auth/signin", HttpMethod.POST, new HttpEntity<>(headers), Object.class);
        return ResponseEntity.ok(Objects.requireNonNull(regUser.getBody()));
    }

    public static void f() {
        User responseEntity = new RestTemplate().getForObject("http://localhost:8082/api/test/all/", User.class);
        System.out.println(responseEntity.toString());
    }

}
