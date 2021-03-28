package com.habr.controller;

import com.habr.repository.ArticleRepository;
import com.habr.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService userService;

    @Autowired
    public ArticleController(ArticleService userService) {
        this.userService = userService;
    }
}
