package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Message;
import com.homeSwap.homeswapbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySenderAndRecipientOrderByCreatedAtAsc(User sender, User recipient);

}
