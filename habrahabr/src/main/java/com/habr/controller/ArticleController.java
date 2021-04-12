package com.habr.controller;

import com.habr.dto.ReactionDTO;
import com.habr.model.Article;
import com.habr.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/{id}/reactions")
    public List<ReactionDTO> getArticleReactions(@PathVariable("id") Long id) {
        return articleService.getArticleReactions(id);
    }
}
