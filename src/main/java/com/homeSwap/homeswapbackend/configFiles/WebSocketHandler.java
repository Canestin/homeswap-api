package com.homeSwap.homeswapbackend.configFiles;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.homeSwap.homeswapbackend.model.Message;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.UserRepository;
import com.homeSwap.homeswapbackend.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

        @Autowired
        private MessagingService messageService;

        @Autowired
        private UserRepository userRepository;

        private Map<Integer, WebSocketSession> sessions = new ConcurrentHashMap<>();

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            // Get the user ID from the session attributes
            Integer userId = (Integer) session.getAttributes().get("userId");

            // Add the session to the sessions map
            sessions.put(userId, session);
        }


    @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            // Get the sender and recipient IDs from the message payload
            String payload = message.getPayload();
            JsonObject jsonObject = new Gson().fromJson(payload, JsonObject.class);
            Integer senderId = jsonObject.get("senderId").getAsInt();
            Integer recipientId = jsonObject.get("recipientId").getAsInt();
            String content = jsonObject.get("content").getAsString();

            // Get the sender and recipient users
            User sender = userRepository.getOne(senderId);
            User recipient = userRepository.getOne(recipientId);

            // Create a new message
            Message newMessage = new Message();
            newMessage.setTextContent(content);
            newMessage.setSender(sender);
            newMessage.setRecipient(recipient);

            // Save the message
            messageService.sendMessage(newMessage);

            // Send the message to the recipient
            WebSocketSession recipientSession = sessions.get(recipientId);
            if (recipientSession != null && recipientSession.isOpen()) {
                recipientSession.sendMessage(new TextMessage(payload));
            }
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            // Get the user ID from the session attributes
            Long userId = (Long) session.getAttributes().get("userId");

            // Remove the session from the sessions map
            sessions.remove(userId);
        }


}
