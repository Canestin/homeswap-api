package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.CommentDTO;
import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.CommentService;
import com.homeSwap.homeswapbackend.service.UserService;
import com.homeSwap.homeswapbackend.service.housingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    housingService houseService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;


    @PostMapping("/add")
    public ResponseEntity<apiResponse> commentHouse(@RequestBody CommentDTO commentDTO) {
        Optional<User> OptionalUser = userService.findUserById(commentDTO.getUserId());
        Optional<housing> OptionalHouse = houseService.getHouseById(commentDTO.getHousingId());

        if (!OptionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!OptionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
        User users=OptionalUser.get();
        housing house=OptionalHouse.get();

        commentService.addComment(commentDTO, users, house);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "Comment added successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/")

    public ResponseEntity<List<CommentDTO>>getAllComments() {

        List<CommentDTO> commentDTOS = commentService.listAllComments();
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }

    //This would get all the comments by house
    @GetMapping(value="/{houseID}")
    public List<CommentDTO> getCommentsByHouseID(@PathVariable(value = "houseID") Integer id) {
        Optional<housing> house = houseService.getHouseById(id);

        return commentService.getCommentsByHouseId(house.get().getId());
    }
}
