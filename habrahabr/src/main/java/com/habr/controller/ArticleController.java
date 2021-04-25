package com.habr.controller;

import com.habr.dto.ArticleDTO;
import com.habr.dto.ReactionCounterDTO;
import com.habr.model.Article;
import com.habr.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ArticleDTO> getArticles(@RequestParam(value = "p", defaultValue = "0") int page,
                                        @RequestParam(value = "s", defaultValue = "2") int size) {
        return articleService.getArticles(page, size);
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/{id}/reactions")
    public List<ReactionCounterDTO> getArticleReactions(@PathVariable("id") Long id) {
        return articleService.getArticleReactions(id);
    }
}
