package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.ChannelDTO;
import com.homeSwap.homeswapbackend.DTO.MessageDTO;
import com.homeSwap.homeswapbackend.model.Channel;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.UserRepository;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.ChannelService2;
import com.homeSwap.homeswapbackend.service.MessagingService;
import com.homeSwap.homeswapbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messaging")
public class MessagingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MessagingService messagingService;

    @Autowired
    UserService userService;

    @Autowired
    ChannelService2 channelService2;

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


    @PostMapping("/add")
    public ResponseEntity<apiResponse> createMessage(@RequestBody MessageDTO messageDTO) {
        Optional<User> optionalSender = userService.findUserById(messageDTO.getSender_id());
        Optional<User> optionalReceiver = userService.findUserById(messageDTO.getRecipient_id());

        Optional<Channel> optionalChannel = channelService2.getChannelById(messageDTO.getChannel_id());

        if (!optionalSender.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!optionalReceiver.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!optionalChannel.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "channel is invalid"), HttpStatus.CONFLICT);
        }

        User sender = optionalSender.get();
        User receiver = optionalReceiver.get();
        Channel channelId = optionalChannel.get();

        messagingService.addMessage(messageDTO, sender, receiver,channelId);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "New message sent"), HttpStatus.CREATED);

    }


    @GetMapping(value="/{channel_id}")
    public List<MessageDTO> getMessagesByChannelId(@PathVariable(value = "channel_id") Integer id) {
        Optional<Channel> channelId = channelService2.getChannelById(id);
        return messagingService.getMessagesByChannelId(channelId.get().getId());
    }



}
