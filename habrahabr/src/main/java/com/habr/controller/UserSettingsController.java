package com.habr.controller;

import com.habr.model.User;
import com.habr.services.UserSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edobr.com/settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateNameSurname(@RequestBody User user) {
        return userSettingsService.updateNameSurname(user);
    }

    @PostMapping(value = "/changeNickname", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateEmail(@RequestBody User user) {
        return userSettingsService.updateEmail(user);
    }

    @PostMapping(value = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updatePassword(@RequestBody User user) {
        return userSettingsService.updatePassword(user);
    }

    @PostMapping(value = "/changeEmail", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateNickname(@RequestBody User user) {
        return userSettingsService.updateNickname(user);
    }
}
