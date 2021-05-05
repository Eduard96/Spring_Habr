package com.habr.controller;

import com.habr.dto.ArticleDTO;
import com.habr.dto.UserDTO;
import com.habr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edobr.com/users")
@Validated
public class UserController {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Autowired
    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(@RequestParam(name = "p", defaultValue = "0") int page,
                                  @RequestParam(name = "s", defaultValue = "5") int size) {
        return userService.getAllUsers(page, size);
    }

    @Autowired
    private WebClient webClient;
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> getUserById(@PathVariable @Min(value = 2, message = "/{id} must be greater than or equal to 2 ") Long id,
                            @RequestHeader HttpHeaders httpHeaders) {
        System.out.println(httpHeaders.toString());
        HttpEntity<String> httpEntity =  new HttpEntity<>("headers", httpHeaders);
        ResponseEntity<String> str = restTemplate.exchange("http://localhost:8082/api/test/all/",
                                                                    HttpMethod.GET, httpEntity, String.class);
        Mono<String> mono = webClient.get().uri("/api/test/all").
                retrieve().bodyToMono(String.class);
        return mono;
        //return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(value = "/{id}/following", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTO>> getFollowing(@PathVariable("id") Long id,
                                                 @RequestParam(name = "p", defaultValue = "0") int page,
                                                 @RequestParam(name = "s", defaultValue = "5") int size) {
        HashMap<Long, List<UserDTO>> ret = new HashMap<>();
        ret.put(id, userService.getFollowing(id, page, size));
        return ret;
    }

    @GetMapping(value = "/{id}/followers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTO>> getFollowers(@PathVariable("id") Long id,
                                                 @RequestParam(name = "p", defaultValue = "0") int page,
                                                 @RequestParam(name = "s", defaultValue = "5") int size) {
        HashMap<Long, List<UserDTO>> ret = new HashMap<>();
        ret.put(id, userService.getFollowers(id, page, size));
        return ret;
    }

    @GetMapping ("/{id}/articles")
    public Map<Long, List<ArticleDTO>> getUserArticles(@PathVariable("id") Long id) {
        HashMap<Long, List<ArticleDTO>> ret = new HashMap<>();
        ret.put(id, userService.getUserArticles(id));
        return ret;
    }
}
