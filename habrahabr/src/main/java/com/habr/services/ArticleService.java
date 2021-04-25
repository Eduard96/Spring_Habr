package com.habr.services;

import com.habr.dto.ArticleDTO;
import com.habr.dto.ReactionCounterDTO;
import com.habr.model.Article;
import com.habr.repository.ArticleRepository;
import com.habr.repository.ReactionCounterRepository;
import com.habr.util.ModelToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ReactionCounterRepository counterRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleDTO> getArticles(int page, int size) {
        List<Article> articles = articleRepository.findAllBy(PageRequest.of(page, size));
        return ModelToDTO.mapList(articles, ArticleDTO.class);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findDistinctById(id);
    }

    @Transactional
    public List<ReactionCounterDTO> getArticleReactions(Long id) {
        Article article = articleRepository.findDistinctById(id);
        return ModelToDTO.mapList(new ArrayList<>(article.getReactionCounter()), ReactionCounterDTO.class);
    }
}
