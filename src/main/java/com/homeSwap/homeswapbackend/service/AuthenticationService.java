package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.model.AuthenticationToken;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
     tokenRepository.save(authenticationToken);

    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
}
