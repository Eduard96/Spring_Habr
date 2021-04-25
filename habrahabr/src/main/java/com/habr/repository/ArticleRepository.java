package com.habr.repository;

import com.habr.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = {"user", "reactionCounter"})
    Article findDistinctById(Long id);

}
