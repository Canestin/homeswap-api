package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.BookingDTO;
import com.homeSwap.homeswapbackend.DTO.MessageDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    private String textContent;

    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    Channel channel;

    public Message() {
    }

    public Message(MessageDTO messageDTO, User senderID, User receiverID, Channel channel) {
        this.sender=senderID;
        this.recipient=receiverID;
        this.textContent = messageDTO.getTextContent();
        this.createdAt=new Date();
        this.channel = channel;

    }
    public Message(User sender, User recipient, String textContent, Channel channel, Date createdAt) {
        this.sender = sender;
        this.recipient = recipient;
        this.textContent = textContent;
        this.channel=channel;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
