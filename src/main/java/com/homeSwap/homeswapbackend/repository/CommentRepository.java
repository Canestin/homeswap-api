package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Comment;
import com.homeSwap.homeswapbackend.model.housing;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findAllByHouse(housing house);
    @Query(value="SELECT c.Id AS id,c.comment_text AS commText,c.user_id AS userid,c.house_id AS houseID FROM comments c WHERE c.house_id = :house_id", nativeQuery=true)
    public List<Tuple> getCommentsByHouseID(@Param("house_id")Integer houseId);
}
