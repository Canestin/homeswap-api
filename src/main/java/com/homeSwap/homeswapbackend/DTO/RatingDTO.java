package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Rating;

public class RatingDTO {
    private Integer id;
    private int rating;
    private Integer housingId;
    private Integer userId;


    public RatingDTO() {
    }

    public RatingDTO(Rating rating)
    {
        this.setId(rating.getId());
        this.setRating(rating.getRating());
        this.setUserId(rating.getUser().getId());
        this.setHousingId(rating.getHouse().getId());
    }

    public RatingDTO(int rating, Integer housingId, Integer userId) {
        this.rating = rating;
        this.housingId = housingId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Integer getHousingId() {
        return housingId;
    }

    public void setHousingId(Integer housingId) {
        this.housingId = housingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
