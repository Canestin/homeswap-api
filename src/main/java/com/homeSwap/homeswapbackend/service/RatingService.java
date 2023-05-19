package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.*;
import com.homeSwap.homeswapbackend.repository.RatingRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public static Rating getRatingFromDto(RatingDTO ratingDTO, User user, housing house) {
        Rating rate = new Rating(ratingDTO, user, house);
        return rate;
    }

    public RatingDTO getDtoFromRating(Rating rate) {


        RatingDTO ratingDTO = new RatingDTO(rate);
        return ratingDTO;
    }

    public void addRating(RatingDTO ratingDTO, User user, housing house) {
        Rating rate = getRatingFromDto(ratingDTO, user, house);
        ratingRepository.save(rate);
    }

    public List<RatingDTO> listAllRatings() {
        List<Rating> rate = ratingRepository.findAll();
        List<RatingDTO> ratingDtoss= new ArrayList<>();
        for (Rating rat : rate) {

            ratingDtoss.add(getDtoFromRating(rat));
        }
        return ratingDtoss;

    }

    public List<RatingDTO> getRatingsByHouseId(Integer houseID){

        //return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> ratings = ratingRepository.getRatingsByHouseID(houseID);
        return ratings.stream()
                .map(this::mapRatingTupleToDTO)
                .collect(Collectors.toList());

    }

    //This method extracts the values of the tuple using the get() method and sets them in a new RatingDTO object
    private RatingDTO mapRatingTupleToDTO(Tuple ratingTuple) {
        Integer id = ratingTuple.get("id", Integer.class);
        Integer rating = ratingTuple.get("rate", Integer.class);
        Integer userID = ratingTuple.get("userid", Integer.class);
        Integer houseID = ratingTuple.get("houseID", Integer.class);

        // Create a new RatingDTO object and set the values
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(id);
        ratingDTO.setRating(rating);
        ratingDTO.setUserId(userID);
        ratingDTO.setHousingId(houseID);

        return ratingDTO;
    }



    public void deleteRating(Integer ratingID){
        ratingRepository.deleteById(ratingID);

    }

    public RatingDTO getRatingsByID(Integer id) {
        Rating rate = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ratings not found"));

        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rate.getId());

        ratingDTO.setHousingId(rate.getHouse().getId());
        ratingDTO.setUserId(rate.getUser().getId());;
        ratingDTO.setRating(rate.getRating());

        return ratingDTO;
    }

    public void updateRating(Integer ratingID, RatingDTO ratingDTO, User user, housing house) {
        Rating rate = getRatingFromDto(ratingDTO, user, house);
        rate.setId(ratingID);
        ratingRepository.save(rate);
    }



}
