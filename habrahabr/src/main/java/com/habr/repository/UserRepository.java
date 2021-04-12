package com.habr.repository;

import com.habr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"following", "followers", "articles", "reactionCounter"})
    Page<User> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = {"following", "followers", "articles", "reactionCounter"})
    User findDistinctById(Long id);
}
