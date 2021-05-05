package com.habr.controller;

import com.habr.dto.RegAuthUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Last step
 */
@RestController
@RequestMapping("/edobr.com")
public class RegistrationAndAuthenticationController {

    private final WebClient webClient;

    @Autowired
    public RegistrationAndAuthenticationController(WebClient webClient) {
        this.webClient = webClient;
    }


    @PostMapping(value = "/signup")
    public Mono<?> signup( @Valid @RequestBody RegAuthUserDTO user) {
        return webClient.post()
                .uri("/api/auth/signup")
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> signin(@RequestBody RegAuthUserDTO user) {
        return webClient.post()
                .uri("/api/auth/signin")
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(String.class);
    }
}
