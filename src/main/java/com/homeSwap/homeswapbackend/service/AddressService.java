package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.model.Address;

import com.homeSwap.homeswapbackend.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    public void saveConfirmationAddress(Address address) {
        addressRepository.save(address);

    }
}
