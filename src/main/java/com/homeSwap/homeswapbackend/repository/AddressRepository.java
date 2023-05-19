package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
