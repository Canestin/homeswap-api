package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.ChannelDTO;

import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.Channel;
import com.homeSwap.homeswapbackend.model.Rating;
import com.homeSwap.homeswapbackend.model.User;

import com.homeSwap.homeswapbackend.repository.ChannelRepository;
import com.homeSwap.homeswapbackend.repository.UserRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChannelService2 {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    UserRepository userRepository;


    public ChannelDTO getDtoFromChannel(Channel channel) {


        ChannelDTO channelDTO = new ChannelDTO(channel);
        return channelDTO;
    }
    public static Channel getChannelFromDto(ChannelDTO channelDTO, User user1, User user2) {
        Channel channel = new Channel(channelDTO, user1, user2);
        return channel;
    }


    public void addChannel(ChannelDTO channelDTO, User user1, User user2) {
        Channel channel = getChannelFromDto(channelDTO, user1, user2);
        channelRepository.save(channel);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<Channel> getChannelById(Integer id) {
        return channelRepository.findById(id);
    }


    public ChannelDTO getChannelByUser1ANDUser2(Integer userId_1, Integer userId_2) {
        Channel channel = channelRepository.findById(userId_1)
                .orElseThrow(() -> new RuntimeException("channel not found"));

        Channel channel2 = channelRepository.findById(userId_2)
                .orElseThrow(() -> new RuntimeException("channel not found"));

        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(channel.getId());

        channelDTO.setUser1_id(channel.getUser1_id().getId());
        channelDTO.setUser2_id(channel.getUser2_id().getId());;
        channelDTO.setLast_message(channel.getLast_message());
        channelDTO.setLast_message_sent_at(channel.getLast_message_sent_at());
        return channelDTO;
    }


    public List<ChannelDTO> getChannelsByUser1IdANDUser2Id(Integer user1, Integer user2){

        //return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> channel = channelRepository.getChannelByUser1AndUser2Id(user1,user2);
        return channel.stream()
                .map(this::mapChannelTupleToDTO)
                .collect(Collectors.toList());

    }

    //This method extracts the values of the tuple using the get() method and sets them in a new RatingDTO object
    private ChannelDTO mapChannelTupleToDTO(Tuple channelTuple) {
        Integer id = channelTuple.get("ID", Integer.class);
        Integer user1 = channelTuple.get("user1_ID", Integer.class);
        Integer user2 = channelTuple.get("user2_ID", Integer.class);
        String lastMessage = channelTuple.get("lastMessage", String.class);
        Date lastMessageSentAt = channelTuple.get("lastMassageSentAt", Date.class);

        // Create a new ChannelDTO object and set the values
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setId(id);
        channelDTO.setLast_message(lastMessage);
        channelDTO.setUser1_id(user1);
        channelDTO.setUser2_id(user2);
        channelDTO.setLast_message_sent_at(lastMessageSentAt);

        return channelDTO;
    }
}
