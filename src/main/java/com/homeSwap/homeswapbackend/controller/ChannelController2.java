package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.ChannelDTO;

import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.ChannelService2;
import com.homeSwap.homeswapbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/channel")
public class ChannelController2 {


   @Autowired
    UserService userService;

   @Autowired
    ChannelService2 channelService2;



    @PostMapping("/add")
    public ResponseEntity<apiResponse> createChannel(@RequestBody ChannelDTO channelDTO) {
        Optional<User> optionalUser1 = channelService2.getUserById(channelDTO.getUser1_id());
        Optional<User> optionalUser2 = channelService2.getUserById(channelDTO.getUser2_id());

        if (!optionalUser1.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!optionalUser2.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        User user1 = optionalUser1.get();
        User user2 = optionalUser2.get();

        channelService2.addChannel(channelDTO, user1, user2);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "created a new channel"), HttpStatus.CREATED);

    }


    //This would get all the channels by user1 and User2 id
    @GetMapping(value="/{userID_1}/{userID_2}")
    public List<ChannelDTO> getHousingsByHouseID(@PathVariable(value = "userID_1") Integer id, @PathVariable(value = "userID_2") Integer id2 ) {
        Optional<User> user1 = userService.findUserById(id);
        Optional<User> user2 = userService.findUserById(id2);
        return channelService2.getChannelsByUser1IdANDUser2Id(user1.get().getId(),user2.get().getId());
    }
}
