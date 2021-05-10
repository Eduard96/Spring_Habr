package com.habr.micro_services;

import com.habr.dto.UserDTOToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegAndAuthService {

    private final RestTemplate restTemplate;

    @Autowired
    public RegAndAuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> signup(UserDTOToService userDTOToService) {
        return restTemplate
                .exchange("http://localhost:8082/api/auth/signup",
                        HttpMethod.POST,
                        new HttpEntity<>(userDTOToService),
                        Object.class);
    }

    public ResponseEntity<?> signin(UserDTOToService userDTOToService) {
        return restTemplate
                .exchange("http://localhost:8082/api/auth/signin",
                        HttpMethod.POST,
                        new HttpEntity<>(userDTOToService),
                        Object.class);
    }

    public String authenticate(HttpHeaders httpHeaders) {
        HttpEntity<String> httpEntity =  new HttpEntity<>("headers", httpHeaders);
        return restTemplate
                .exchange("http://localhost:8082/api/auth/",
                        HttpMethod.GET,
                        httpEntity,
                        String.class).getBody();
    }
}
