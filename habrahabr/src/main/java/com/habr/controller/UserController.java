package com.habr.controller;

import com.habr.dto.ArticleDTO;
import com.habr.dto.UserDTO;
import com.habr.model.User;
import com.habr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edobr.com/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(@RequestParam(name = "p", defaultValue = "0") int page,
                                  @RequestParam(name = "s", defaultValue = "2") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/{id}/following", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTO>> getFollowing(@PathVariable("id") Long id) {
        HashMap<Long, List<UserDTO>> ret = new HashMap<>();
        ret.put(id, userService.getFollowing(id));
        return ret;
    }

    @GetMapping(value = "/{id}/followers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long, List<UserDTO>> getFollowers(@PathVariable("id") Long id) {
        HashMap<Long, List<UserDTO>> ret = new HashMap<>();
        ret.put(id, userService.getFollowers(id));
        return ret;
    }

    @GetMapping ("/{id}/articles")
    public Map<Long, List<ArticleDTO>> getUserArticles(@PathVariable("id") Long id) {
        HashMap<Long, List<ArticleDTO>> ret = new HashMap<>();
        ret.put(id, userService.getUserArticles(id));
        return ret;
    }
}
