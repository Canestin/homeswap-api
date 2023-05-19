package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.model.Apartment;
import com.homeSwap.homeswapbackend.repository.ApartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class apartmentService {

    //creating an apartment business logic
    @Autowired
    ApartmentRepo apartmentRepo;
    public void createApartment(Apartment apartment){
        apartmentRepo.save(apartment);
    }

    public List<Apartment> listApartment(){
        return apartmentRepo.findAll();
    }

    public Optional<Apartment> getApartmentById(Integer ApartmentId){
        return apartmentRepo.findById(ApartmentId);
    }

    //testing
    public  Optional<Apartment> getApartmentById2(Integer id) {
        return apartmentRepo.findById(id);
    }

    public void deleteApartment(Integer apartmentID){
        apartmentRepo.deleteById(apartmentID);

    }

    // this will read apartment by name
    public Apartment readApartment(String apartmentName) {
        return apartmentRepo.findByApartmentName(apartmentName);
    }

    public void updateApartment(Integer apartID, Apartment newApartment)
    {
        Apartment apartment=apartmentRepo.findById(apartID).get(); // this will get apartment id
        apartment.setApartmentName(newApartment.getApartmentName());
        apartment.setApartmentType(newApartment.getApartmentType());
        apartment.setDescription(newApartment.getDescription());

        apartmentRepo.save(apartment);

    }
}
