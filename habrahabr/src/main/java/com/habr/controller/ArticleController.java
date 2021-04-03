package com.habr.controller;

import com.habr.model.Article;
import com.habr.repository.ArticleRepository;
import com.habr.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edobr.com/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{id}")
    public List<Article> getArticlesById(@PathVariable("id") Long id) {
        return articleService.getArticlesByUserId(id);
    }
}
