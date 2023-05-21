package com.homeSwap.homeswapbackend.repository;

import com.homeSwap.homeswapbackend.model.Channel;
import com.homeSwap.homeswapbackend.model.User;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    Channel findByUser1AndUser2(User user1, User user2);

    @Query(value="SELECT c.Id AS ID,c.last_message AS lastMessage,c.last_message_sent_at AS lastMassageSentAt,c.user1_id AS user1_ID, c.user2_id AS user2_ID FROM channel c WHERE c.user1_id = :user1_id AND c.user2_id = :user2_id", nativeQuery=true)
    public List<Tuple> getChannelByUser1AndUser2Id(@Param("user1_id")Integer user1_Id,@Param("user2_id")Integer user2_Id);
}
