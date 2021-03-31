package com.habr.repository;

import com.habr.dto.UserDTO;
import com.habr.model.Followers;
import com.habr.model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"following", "followers", "articles", "reactionCounter"})
    List<User> findAll();

    @EntityGraph(attributePaths = {"following", "followers", "articles", "reactionCounter"})
    User findDistinctById(Long id);

//    @EntityGraph(attributePaths = {"following", "followers", "articles", "reactionCounter"})
//    List<User> findUsersByFollowing(List<User> following);
    //
    @EntityGraph( type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"following", "followers", "articles", "reactionCounter"})
    User getDistinctById(Long id);

}
