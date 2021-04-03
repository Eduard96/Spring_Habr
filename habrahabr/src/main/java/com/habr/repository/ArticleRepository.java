package com.habr.repository;

import com.habr.model.Article;
import com.habr.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @EntityGraph(attributePaths = {"user", "reactionCounter"})
    List<Article> findAll();

    @EntityGraph(attributePaths = {"user", "reactionCounter"})
    List<Article> findAllByUserId(Long user_id);
}
