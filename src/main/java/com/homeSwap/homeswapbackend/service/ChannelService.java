package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.model.Channel;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    @Autowired
    ChannelRepository channelRepository;


    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public Channel getChannelByUsers(User user1, User user2) {
        return channelRepository.findByUser1AndUser2(user1, user2);
    }

    public Channel saveChannel(Channel channel) {
        return channelRepository.save(channel);
    }

}
