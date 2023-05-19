package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.Rating;
import com.homeSwap.homeswapbackend.model.housing;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findAllByHouse(housing house);
    //Using native query to get ratings by house
    @Query(value="SELECT r.Id AS id,r.rating AS rate,r.user_id AS userid,r.house_id AS houseID FROM ratings r WHERE r.house_id = :house_id", nativeQuery=true)
    public List<Tuple> getRatingsByHouseID(@Param("house_id")Integer houseId);

}
