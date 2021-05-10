package com.habr.repository;

import com.habr.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //I am using findAllBy instead of findAll to set return type List instead of Page
    //Because JpaRepository and CrudRepository and PagingAndSortingRepository have this function
    //And we can't change just return type without changing rest of signature of function
    List<User> findAllBy(Pageable pageable);

    //Use EntityGraph if you need child entities
    @EntityGraph(attributePaths = {"followers", "articles", "reactionCounter"})
    User findDistinctById(Long id);

    List<User> findByFollowersId(Long followers_id, Pageable pageable);

    //if someone read this comment, Do you know, which function I need use to avoid custom query?
    @Query(nativeQuery = true, value = "select distinct * from user u right outer join " +
            "(select followers_id from user_followers  where User_id = :user_id order by followers_id limit :page, :size)" +
            " as uffi on u.id = uffi.followers_id order by uffi.followers_id")
    List<User> findByUserId(@Param("user_id") Long user_id, @Param("page") int page, @Param("size") int size);

    //My query is better than hibernate generate, but now, it doesn't matter
    //I'll use countAllByFollowersId
//    @Query(nativeQuery = true, value = "select COUNT(*) as following_count from user_followers where followers_id= :id")
//    int getFollowingNumber(@Param("id") Long id);

    int countAllByFollowersId(Long followers_id);


    /*
     * //    @Query(nativeQuery = true, value = "SELECT * \n" +
     * //            "FROM   user u \n" +
     * //            "       LEFT OUTER JOIN user_followers uf\n" +
     * //            "                    ON u.id = uf.followers_id\n" +
     * //            "       LEFT OUTER JOIN user u2\n" +
     * //            "                    ON uf.user_id = u2.id\n" +
     * //            "WHERE  u2.id = :user_id ")
     */
}
