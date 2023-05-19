package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Integer> {

    Apartment findByApartmentName(String categoryName);
}
