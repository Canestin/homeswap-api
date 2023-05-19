package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Booking;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    //Using native query to get ratings by house
    @Query(value="SELECT b.Id AS id,b.number_Of_Travellers AS numTravel,b.number_Of_Pets AS numPets,b.check_In_Date_Time AS CheckInDate,b.check_Out_Date_Time AS checkOutDate,b.booking_Status AS bookStatus,b.booking_date AS bookDate,b.user_id AS userID,b.house_id AS houseID FROM booking b WHERE b.house_id = :house_id", nativeQuery=true)
    public List<Tuple> getBookingsByHouseID(@Param("house_id")Integer houseId);

}

