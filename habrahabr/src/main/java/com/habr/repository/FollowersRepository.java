package com.habr.repository;

import com.habr.model.Followers;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @EntityGraph(attributePaths = {"followers", "following"})
    List<Followers> findAllByFollowingId(Long following_id);
}
