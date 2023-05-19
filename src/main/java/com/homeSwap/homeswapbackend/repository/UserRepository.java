package com.homeSwap.homeswapbackend.repository;


import com.homeSwap.homeswapbackend.model.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    @Modifying
    @Transactional
    @Query( value="UPDATE users u SET u.enabled = true WHERE u.id= :id", nativeQuery=true)
    public void enable(@Param("id")Integer id);

    @Query( value="SELECT * FROM users u WHERE u.verification_code = :verification_code", nativeQuery=true)
    public User findByVerificationCode(@Param("verification_code")String code);


}
