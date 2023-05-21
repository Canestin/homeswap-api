package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.ChannelDTO;
import com.homeSwap.homeswapbackend.DTO.MessageDTO;
import com.homeSwap.homeswapbackend.model.Channel;
import com.homeSwap.homeswapbackend.model.Message;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.MessageRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
        message.setCreatedAt(new Date());

        // Save the message
        messageRepository.save(message);
    }



    public MessageDTO getDtoFromMessage(Message message) {


        MessageDTO messageDTO = new MessageDTO(message);
        return messageDTO;
    }
    public static Message getMessagesFromDto(MessageDTO messageDTO, User sender_id, User receiver_id,Channel channel_id) {
        Message message = new Message(messageDTO, sender_id, receiver_id, channel_id);
        return message;
    }

    public void addMessage(MessageDTO messageDTO, User sender_id, User receiver_id, Channel channel_id) {
        Message message = getMessagesFromDto(messageDTO, sender_id, receiver_id,channel_id);
        messageRepository.save(message);
    }

    private MessageDTO mapMessageTupleToDTO(Tuple messageTuple) {
        Integer id = messageTuple.get("ID", Integer.class);
        Integer sender = messageTuple.get("senderID", Integer.class);
        Integer receiver = messageTuple.get("recipientID", Integer.class);
        String textContent = messageTuple.get("textContent", String.class);
        Date createdAt = messageTuple.get("createdAt", Date.class);
        Integer channelID = messageTuple.get("channelID", Integer.class);

        // Create a new ChannelDTO object and set the values
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(id);
        messageDTO.setSender_id(sender);
        messageDTO.setRecipient_id(receiver);
        messageDTO.setTextContent(textContent);
        messageDTO.setCreatedAt(createdAt);
        messageDTO.setChannel_id(channelID);

        return messageDTO;
    }

    public List<MessageDTO> getMessagesByChannelId(Integer channel_id){

        //return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> message = messageRepository.getMessagesByChannelId(channel_id);
        return message.stream()
                .map(this::mapMessageTupleToDTO)
                .collect(Collectors.toList());

    }
}
