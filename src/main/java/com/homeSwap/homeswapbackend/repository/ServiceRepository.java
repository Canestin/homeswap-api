package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.ServiceModel;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Integer> {

    //Using native query to get constraint by house
    @Query(value="SELECT s.Id AS id,s.service_text AS serveText, housing_id AS houseID FROM service s WHERE s.housing_id = :house_id", nativeQuery=true)
    public List<Tuple> getServiceByHouseID(@Param("house_id")Integer houseId);
}
