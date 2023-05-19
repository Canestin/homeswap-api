package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Constraint;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstraintRepository extends JpaRepository<Constraint, Integer> {

    //Using native query to get constraint by house
    @Query(value="SELECT c.Id AS id,c.constraint_text AS conText, housing_id AS houseID FROM house_constraint c WHERE c.housing_id = :house_id", nativeQuery=true)
    public List<Tuple> getConstraintsByHouseID(@Param("house_id")Integer houseId);
}
