package com.habr.services;

import com.habr.dto.ArticleDTO;
import com.habr.dto.ReactionDTO;
import com.habr.model.Article;
import com.habr.model.ReactionCounter;
import com.habr.repository.ArticleRepository;
import com.habr.repository.ReactionCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ReactionCounterRepository counterRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ReactionCounterRepository counterRepository) {
        this.articleRepository = articleRepository;
        this.counterRepository = counterRepository;
    }

    public List<ArticleDTO> getArticles(int page, int size) {
        List<Article> articles = articleRepository.findAllBy(PageRequest.of(page, size));
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (Article article : articles) {
            articleDTOS.add(new ArticleDTO(article));
        }
        return articleDTOS;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findDistinctById(id);
    }

    @Transactional
    public List<ReactionDTO> getArticleReactions(Long id) {
        List<ReactionCounter> reactionCounters = counterRepository.findAllByArticleId(id);
        List<ReactionDTO> reactions = new ArrayList<>();
        for (ReactionCounter reactionCounter : reactionCounters) {
            reactions.add(new ReactionDTO(reactionCounter.getReaction(), reactionCounter.getUser_id()));
        }
        return reactions;
    }
}
