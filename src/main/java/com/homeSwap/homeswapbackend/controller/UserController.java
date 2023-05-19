package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.user.*;
import com.homeSwap.homeswapbackend.checkNull.Helper;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public ResponseDTO signup(@RequestBody SignUpDTO signUpDTO, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

        return userService.signUp(signUpDTO,request);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {

        boolean verified=userService.verify(code);
        if (verified) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @PostMapping("/sign_in")
   public SignInResponseDTO signin(@RequestBody SignInDTO signInDTO){

        return userService.signIn(signInDTO);
    }

    @GetMapping("/")

    public ResponseEntity<List<SignUpDTO>> getUsers() {

        List<SignUpDTO> body = userService.listUsers();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @GetMapping(value="/{Id}")
    public SignUpDTO UserByID(@PathVariable(value = "Id") Integer id) {
        return userService.getUserById(id);
    }


    //testing getuserById again





    @PostMapping("/update/{userID}")
    public ResponseEntity<apiResponse> updateUser(@PathVariable("userID") Integer userID, @RequestBody User user) {
        // Check to see if the user exists.
        if (Helper.notNull(userService.getUserById(userID))) {
            // If the user exists then update it.
            userService.updateUser(userID, user);
            return new ResponseEntity<apiResponse>(new apiResponse(true, "updated the user"), HttpStatus.OK);
        }

        // If the apartment doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<apiResponse>(new apiResponse(false, "user does not exist"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "user has been deleted"), HttpStatus.OK);
    }

}
