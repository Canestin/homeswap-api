package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.ChannelDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    User user2;

    private String last_message;

    private Date last_message_sent_at;

    public Channel() {
    }


    public Channel(ChannelDTO channelDTO, User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.last_message = channelDTO.getLast_message();
        this.last_message_sent_at= new Date();


    }
    public Channel(User user1, User user2, String last_message, LocalDateTime last_message_sent_at) {
        this.user1 = user1;
        this.user2 = user2;
        this.last_message = last_message;
        this.last_message_sent_at = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser1_id() {
        return user1;
    }

    public void setUser1_id(User user1) {
        this.user1 = user1;
    }

    public User getUser2_id() {
        return user2;
    }

    public void setUser2_id(User user2) {
        this.user2 = user2;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public Date getLast_message_sent_at() {
        return last_message_sent_at;
    }

    public void setLast_message_sent_at(Date last_message_sent_at) {
        this.last_message_sent_at = last_message_sent_at;
    }
}
