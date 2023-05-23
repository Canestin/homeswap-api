package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Comment;
import com.homeSwap.homeswapbackend.model.Rating;

public class CommentDTO {

    private Integer id;
    private String comment_text;
    private Integer housingId;
    private Integer userId;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment)
    {
        this.setId(comment.getId());
        this.setComment_text(comment.getComment_text());
        this.setUserId(comment.getUser().getId());
        this.setHousingId(comment.getHouse().getId());
    }

    public CommentDTO(String comment_text, Integer housingId, Integer userId) {
        this.comment_text = comment_text;
        this.housingId = housingId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
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
