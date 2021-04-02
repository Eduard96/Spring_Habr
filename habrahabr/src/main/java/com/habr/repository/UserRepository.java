package com.habr.repository;

import com.habr.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
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
    @Transactional
    User getDistinctById(Long id);

//    @Query(value = "SELECT DISTINCT * from user u right outer join user_user uu ON u.id IN " +
//            "(SELECT DISTINCT uu.followed_id from user_user uu where uu.follower_id = :id)",
//        nativeQuery = true)
//    Set<User> getAllFollowingById(@Param("id") Long id);
}
