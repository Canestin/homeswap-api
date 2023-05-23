package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.CommentDTO;
import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.Comment;
import com.homeSwap.homeswapbackend.model.Rating;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.repository.CommentRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public static Comment getCommentFromDto(CommentDTO commentDTO, User user, housing house) {
        Comment comment = new Comment(commentDTO, user, house);
        return comment;
    }

    public CommentDTO getDtoFromRating(Comment comment) {


        CommentDTO commentDTO = new CommentDTO(comment);
        return commentDTO;
    }
    public void addComment(CommentDTO commentDTO, User user, housing house) {
        Comment comment = getCommentFromDto(commentDTO, user, house);
        commentRepository.save(comment);
    }

    public List<CommentDTO> listAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comm : comments) {

            commentDTOS.add(getDtoFromRating(comm));
        }
        return commentDTOS;
    }

    public List<CommentDTO> getCommentsByHouseId(Integer houseID){

        //return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> comments = commentRepository.getCommentsByHouseID(houseID);
        return comments.stream()
                .map(this::mapCommentTupleToDTO)
                .collect(Collectors.toList());

    }

    private CommentDTO mapCommentTupleToDTO(Tuple commentTuple) {
        Integer id = commentTuple.get("id", Integer.class);
        String comment = commentTuple.get("commText", String.class);
        Integer userID = commentTuple.get("userid", Integer.class);
        Integer houseID = commentTuple.get("houseID", Integer.class);

        // Create a new RatingDTO object and set the values
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setComment_text(comment);
        commentDTO.setUserId(userID);
        commentDTO.setHousingId(houseID);

        return commentDTO;
    }

    public void deleteComment(Integer commentID){
        commentRepository.deleteById(commentID);

    }

    public void updateComment(Integer commentID, CommentDTO commentDTO, User user, housing house) {
        Comment comm = getCommentFromDto(commentDTO, user, house);
        comm.setId(commentID);
        commentRepository.save(comm);
    }
}
