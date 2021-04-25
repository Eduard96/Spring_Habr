package com.habr.repository;

import com.habr.model.ReactionCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionCounterRepository extends JpaRepository<ReactionCounter, Long> {

    //@EntityGraph(attributePaths = {"reaction", "user", "article"})
    ReactionCounter findByArticleId(Long article_id);
}
