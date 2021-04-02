package com.habr.controller;

import com.habr.dto.UserDTO;
import com.habr.model.User;
import com.habr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/edobr.com/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/following", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTO>> getFollowing(@RequestParam("id") Long id) {
        HashMap<Long, List<UserDTO>> ret = new HashMap<>();
        ret.put(id, userService.getFollowing(id));
        return ret;
    }
}
