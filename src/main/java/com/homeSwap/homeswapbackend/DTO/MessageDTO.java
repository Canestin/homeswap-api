package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Message;

import java.util.Date;

public class MessageDTO {

    private Integer id;
    private Integer sender_id;
    private Integer recipient_id;
    private String textContent;
    private Date createdAt;
    private Integer channel_id;

    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        this.setId(message.getId());
        this.setSender_id(message.getSender().getId());
        this.setRecipient_id(message.getRecipient().getId());
        this.setTextContent(message.getTextContent());
        this.setCreatedAt(message.getCreatedAt());
        this.setChannel_id(message.getChannel().getId());
    }

    public MessageDTO(Integer sender_id, Integer recipient_id, String textContent, Date createdAt, Integer channel_id) {
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.textContent = textContent;
        this.createdAt = createdAt;
        this.channel_id = channel_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public Integer getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(Integer recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }
}
