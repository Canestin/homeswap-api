package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.CommentDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    housing house;

    private String comment_text;

    public Comment() {
    }

    public Comment(CommentDTO commentDTO, User user, housing house) {
        this.comment_text=commentDTO.getComment_text();
        this.user=user;
        this.house = house;
    }

    public Comment(User user, housing house, String comment_text) {
        this.user = user;
        this.house = house;
        this.comment_text = comment_text;
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

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }
}
