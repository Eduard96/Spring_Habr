package com.habr.repository;

import com.habr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@EntityGraph(attributePaths = {"followers", "articles", "reactionCounter"})
    Page<User> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"followers", "articles", "reactionCounter"})
    User findDistinctById(Long id);

    @EntityGraph(attributePaths = {"followers", "articles", "reactionCounter"})
    Set<User> findByFollowersId(Long followers_id);

//    @EntityGraph(attributePaths = {"followers", "articles", "reactionCounter"})
    @Query(nativeQuery = true, value = "select distinct * from user u right outer join " +
            "(select followers_id from user_followers  where User_id = :user_id order by followers_id limit :page, :size)" +
            " as uffi on u.id = uffi.followers_id order by uffi.followers_id")
    Set<User> findByUserId(@Param("user_id") Long user_id, @Param("page") int page, @Param("size") int size);

    @Query(nativeQuery = true, value = "select COUNT(*) as following_count from user_followers where followers_id= :id")
    int getFollowingNumber(@Param("id") Long id);

//    @EntityGraph(attributePaths = {"followers", "articles", "reactionCounter"})
//    Page<User> findDistinctById(User user, Pageable pageable);

    /**
     * //    @Query(nativeQuery = true, value = "SELECT * \n" +
     * //            "FROM   user u \n" +
     * //            "       LEFT OUTER JOIN user_followers uf\n" +
     * //            "                    ON u.id = uf.followers_id\n" +
     * //            "       LEFT OUTER JOIN user u2\n" +
     * //            "                    ON uf.user_id = u2.id\n" +
     * //            "WHERE  u2.id = :user_id ")
     */

}
