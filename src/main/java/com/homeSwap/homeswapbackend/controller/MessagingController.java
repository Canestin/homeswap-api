package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

@RestController
@RequestMapping("/api/messaging")
public class MessagingController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/connect")
    public ResponseEntity<?> connect(@RequestParam String email) {
        // Find the user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Create a WebSocket session and add the user ID to the session attributes
        WebSocketSession session = (WebSocketSession) RequestContextHolder.currentRequestAttributes().getAttribute(WebSocketHandler.class.getName() + ".SESSION", RequestAttributes.SCOPE_SESSION);
        session.getAttributes().put("userId", user.getId());

        // Return a success response
        return ResponseEntity.ok().build();
    }
}
