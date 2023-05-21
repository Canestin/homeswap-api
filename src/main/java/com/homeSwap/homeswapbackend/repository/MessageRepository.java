package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Message;
import com.homeSwap.homeswapbackend.model.User;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySenderAndRecipientOrderByCreatedAtAsc(User sender, User recipient);

    @Query(value="SELECT m.Id AS ID,m.text_content AS textContent,m.sender_id AS senderID,m.recipient_id AS recipientID, m.created_at AS createdAt, m.channel_id AS channelID FROM messages m WHERE m.channel_id = :channel_id", nativeQuery=true)
    public List<Tuple> getMessagesByChannelId(@Param("channel_id")Integer channel_id);

}
