package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.housing;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingRepository extends JpaRepository<housing, Integer> {

    // Using native query to get housing by user
    @Query(value = "SELECT h.Id AS id,h.photo_one AS photoOne,h.photo_two AS photoTwo,h.photo_three AS photoThree,h.date_created AS createdDate,h.address AS Address,h.country AS Country,h.state AS stat,h.city AS cit,h.zipcode AS zip,h.number_of_travellers AS numOfTravel,h.number_of_bedrooms AS numOfBedroom,h.number_of_beds AS numOfBeds,h.number_of_bathrooms AS numOfBath,h.house_amenities AS houseAmen,h.ad_title AS adTitle,h.description AS Descriptions,h.user_id AS userID  FROM housing h WHERE h.user_id = :user_id", nativeQuery = true)
    public List<Tuple> getHousingByUserID(@Param("user_id") Integer user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM housing WHERE housing.user_id = :user_id", nativeQuery = true)
    public void deleteUserWithHousing(@Param("user_id") Integer userId);

    @Query(value = "SELECT * FROM housing WHERE housing.user_id = :user_id", nativeQuery = true)
    public List<housing> getHousingID(@Param("user_id") Integer userId);
}
