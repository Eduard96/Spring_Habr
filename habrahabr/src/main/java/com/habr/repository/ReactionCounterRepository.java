package com.habr.repository;

import com.habr.model.ReactionCounter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionCounterRepository extends JpaRepository<ReactionCounter, Long> {

    @EntityGraph(attributePaths = {"reaction", "user", "article"})
    List<ReactionCounter> findAllByArticleId(Long article_id);
}
