package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Channel;


import java.util.Date;

public class ChannelDTO {

    private Integer id;

    private Integer user1_id;

    private Integer user2_id;

    private String last_message;

    private Date last_message_sent_at;


    public ChannelDTO() {


    }

    public ChannelDTO(Channel channel)
    {
        this.setId(channel.getId());
        this.setUser1_id(channel.getUser1_id().getId());
        this.setUser2_id(channel.getUser2_id().getId());
        this.setLast_message(channel.getLast_message());
        this.setLast_message_sent_at(channel.getLast_message_sent_at());
    }

    public ChannelDTO(Integer user1_id, Integer user2_id, String last_message, Date last_message_sent_at) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.last_message = last_message;
        this.last_message_sent_at = last_message_sent_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(Integer user1_id) {
        this.user1_id = user1_id;
    }

    public Integer getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(Integer user2_id) {
        this.user2_id = user2_id;
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
