package com.habr.controller;

import com.habr.db_services.UserService;
import com.habr.dto.ArticleDTO;
import com.habr.dto.UserDTOToClient;
import com.habr.micro_services.RegAndAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edobr.com/users")
@Validated
public class UserController {

    private final UserService userService;
    private final RegAndAuthService regAndAuthService;

    @Autowired
    public UserController(UserService userService, RegAndAuthService regAndAuthService) {
        this.userService = userService;
        this.regAndAuthService = regAndAuthService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTOToClient> getAllUsers(@RequestParam(name = "p", defaultValue = "0") int page,
                                             @RequestParam(name = "s", defaultValue = "5") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  getUserById(@PathVariable @Min(value = 2, message = "/{id} must be greater than or equal to 2 ") Long id,
                            @RequestHeader HttpHeaders httpHeaders) {
        String requestUsername = regAndAuthService.authenticate(httpHeaders);
        String pageUsername = userService.getUserById(id).getUserName();
        if(requestUsername.equals(pageUsername)) {
            return ResponseEntity.ok("Owner");
        }
        return ResponseEntity.ok("Not Owner");
    }


    @GetMapping(value = "/{id}/following", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTOToClient>> getFollowing(@PathVariable("id") Long id,
                                                         @RequestParam(name = "p", defaultValue = "0") int page,
                                                         @RequestParam(name = "s", defaultValue = "5") int size) {
        HashMap<Long, List<UserDTOToClient>> ret = new HashMap<>();
        ret.put(id, userService.getFollowing(id, page, size));
        return ret;
    }

    @GetMapping(value = "/{id}/followers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTOToClient>> getFollowers(@PathVariable("id") Long id,
                                                         @RequestParam(name = "p", defaultValue = "0") int page,
                                                         @RequestParam(name = "s", defaultValue = "5") int size) {
        HashMap<Long, List<UserDTOToClient>> ret = new HashMap<>();
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
