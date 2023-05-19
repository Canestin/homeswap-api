package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.AuthenticationToken;
import com.homeSwap.homeswapbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
}
