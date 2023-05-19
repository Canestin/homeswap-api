package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    housing house;

    private int rating;

    public Rating() {
    }

    public Rating(RatingDTO ratingDTO, User user, housing house) {
        this.rating=ratingDTO.getRating();
        this.user=user;
        this.house = house;
    }

    public Rating(User user, housing house, int rating) {
        this.user = user;
        this.house = house;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public housing getHouse() {
        return house;
    }

    public void setHouse(housing house) {
        this.house = house;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
