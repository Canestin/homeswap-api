package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.model.Message;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessagingService {

    @Autowired
    MessageRepository messageRepository;

    public void MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(User sender, User recipient) {
        return messageRepository.findBySenderAndRecipientOrderByCreatedAtAsc(sender, recipient);
    }

    public void sendMessage(Message message) {
        // Set the timestamp
        message.setCreatedAt(LocalDateTime.now());

        // Save the message
        messageRepository.save(message);
    }
}
