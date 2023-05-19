package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.RatingDTO;

import com.homeSwap.homeswapbackend.model.Rating;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.RatingService;
import com.homeSwap.homeswapbackend.service.UserService;
import com.homeSwap.homeswapbackend.service.housingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    housingService houseService;

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;



    @PostMapping("/add")
    public ResponseEntity<apiResponse> rateHouse(@RequestBody RatingDTO ratingDTO) {
        Optional<User> OptionalUser = userService.findUserById(ratingDTO.getUserId());
        Optional<housing> OptionalHouse = houseService.getHouseById(ratingDTO.getHousingId());

        if (!OptionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!OptionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
         User users=OptionalUser.get();
         housing house=OptionalHouse.get();

        ratingService.addRating(ratingDTO, users, house);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "created a new rating"), HttpStatus.CREATED);
    }

    @GetMapping("/")

    public ResponseEntity<List<RatingDTO>>getAllConstraints() {

        List<RatingDTO> rate = ratingService.listAllRatings();
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    //This would get all the ratings by house
    @GetMapping(value="House/{houseID}")
    public List<RatingDTO> getRatingsByHouseID(@PathVariable(value = "houseID") Integer id) {
        Optional<housing> house = houseService.getHouseById(id);

        return ratingService.getRatingsByHouseId(house.get().getId());
    }

    @GetMapping(value="/{Id}")
    public ResponseEntity<RatingDTO> getRatingsByID(@PathVariable(value = "Id") Integer id) {
        RatingDTO rate = ratingService.getRatingsByID(id);
        return ResponseEntity.ok().body(rate);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteRatings(@PathVariable("id") Integer id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "rating has been deleted"), HttpStatus.OK);
    }



    @PostMapping("/update/{ratingID}")
    public ResponseEntity<apiResponse> updateHousing(@PathVariable("ratingID") Integer ratingID, @RequestBody RatingDTO ratingDTO) {
        Optional<User> OptionalUser = userService.findUserById(ratingDTO.getUserId());
        Optional<housing> OptionalHouse = houseService.getHouseById(ratingDTO.getHousingId());

        if (!OptionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!OptionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
        User users=OptionalUser.get();
        housing house=OptionalHouse.get();

        ratingService.updateRating(ratingID, ratingDTO, users,house);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "rating has been updated"), HttpStatus.OK);
    }
}
