package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.BookingDTO;
import com.homeSwap.homeswapbackend.DTO.HousingDto;
import com.homeSwap.homeswapbackend.model.Rating;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class housingService {

    // creating an apartment business logic
    @Autowired
    HousingRepository houseRepo;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ConstraintRepository constraintRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    public HousingDto getDtoFromHousing(housing house) {

        HousingDto houseDto = new HousingDto(house);
        return houseDto;
    }

    public static housing getHouseFromDto(HousingDto houseDto) {
        housing house = new housing(houseDto);
        return house;
    }

    public void addHouse(HousingDto houseDto) {
        housing house = getHouseFromDto(houseDto);
        houseRepo.save(house);
    }

    // TESTING ADD HOUSING
    public housing addHouse2(Integer userId, Integer apartId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null /* @ToReplace || apartment == null */) {
            return null;
        }
        housing house = new housing();
        house.setPhoto_one(house.getPhoto_one());
        house.setPhoto_two(house.getPhoto_two());
        house.setPhoto_three(house.getPhoto_three());
        house.setDate_created(house.getDate_created());
        house.setAddress(house.getAddress());
        house.setCountry(house.getCountry());
        house.setState(house.getState());
        house.setCity(house.getCity());
        house.setZipcode(house.getZipcode());
        house.setNumber_of_travellers(house.getNumber_of_travellers());
        house.setNumber_of_bathrooms(house.getNumber_of_bathrooms());
        house.setNumber_of_bedrooms(house.getNumber_of_bedrooms());
        house.setNumber_of_beds(house.getNumber_of_beds());
        house.setHouse_amenities(house.getHouse_amenities());
        house.setAd_title(house.getAd_title());
        house.setDescription(house.getDescription());
        house.setUser_id(house.getUser_id());

        houseRepo.save(house);

        return house;
    }

    public List<HousingDto> listHousing() {
        List<housing> houses = houseRepo.findAll();
        List<HousingDto> houseDtoss = new ArrayList<>();
        for (housing house : houses) {
            houseDtoss.add(getDtoFromHousing(house));
        }
        return houseDtoss;
    }

    public HousingDto getHousesById(Integer id) {
        housing house = houseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("housing not found"));

        HousingDto housingDto = new HousingDto();
        housingDto.setId(house.getId());
        housingDto.setPhoto_one(house.getPhoto_one());
        housingDto.setPhoto_two(house.getPhoto_two());
        housingDto.setPhoto_three(house.getPhoto_three());
        housingDto.setDate_created(house.getDate_created());
        housingDto.setAddress(house.getAddress());
        housingDto.setCountry(house.getCountry());
        housingDto.setState(house.getState());
        housingDto.setCity(house.getCity());
        housingDto.setZipcode(house.getZipcode());
        housingDto.setDescription(house.getDescription());
        housingDto.setNumber_of_travellers(house.getNumber_of_travellers());
        housingDto.setNumber_of_bathrooms(house.getNumber_of_bathrooms());
        housingDto.setNumber_of_bedrooms(house.getNumber_of_bedrooms());
        housingDto.setNumber_of_beds(house.getNumber_of_beds());
        housingDto.setHouse_amenities(house.getHouse_amenities());
        housingDto.setAd_title(house.getAd_title());
        housingDto.setUser_id(house.getUser_id());

        return housingDto;
    }

    public void updateHouse(Integer houseID, HousingDto housingDto) {
        housing house = getHouseFromDto(housingDto);
        house.setId(houseID);
        houseRepo.save(house);
    }

    public Optional<housing> getHouseById(Integer houseId) {
        return houseRepo.findById(houseId);
    }

    // without optional
    public housing getHouseByID(Integer houseId) {
        return houseRepo.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));

    }

    @Transactional
    public void deleteHouse(Integer houseID) {
        houseRepo.deleteById(houseID);
    }

    // TESTING
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Rating a house
    public Rating rateHouse(Integer userId, Integer houseId, int rating) {
        User user = new User();
        user.setId(userId);
        housing house = new housing();
        house.setId(houseId);
        Rating ratings = new Rating();
        ratings.setUser(user);
        ratings.setHouse(house);
        ratings.setRating(rating);
        return ratingRepository.save(ratings);
    }

    // get ratings for a house
    public Optional<Rating> getRatingsForHouse(Integer houseId) {
        housing house = getHouseByID(houseId);
        return ratingRepository.findAllByHouse(house);
    }

    // This would list all housings added by a user
    public List<HousingDto> getHousingsByUId(Integer userID) {
        List<Tuple> housings = houseRepo.getHousingByUserID(userID);
        return housings.stream()
                .map(this::mapHousingTupleToDTO)
                .collect(Collectors.toList());
    }

    // This method extracts the values of the tuple using the get() method and sets
    // them in a new RatingDTO object
    private HousingDto mapHousingTupleToDTO(Tuple housingTuple) {
        Integer id = housingTuple.get("id", Integer.class);
        String photoOne = housingTuple.get("photoOne", String.class);
        String photoTwo = housingTuple.get("photoTwo", String.class);
        String photoThree = housingTuple.get("photoThree", String.class);
        Date createdDate = housingTuple.get("createdDate", Date.class);
        String address = housingTuple.get("Address", String.class);
        String country = housingTuple.get("Country", String.class);
        String state = housingTuple.get("stat", String.class);
        String city = housingTuple.get("cit", String.class);
        String zipcode = housingTuple.get("zip", String.class);
        Integer NumberOfTravellers = housingTuple.get("numOfTravel", Integer.class);
        Integer NumberOfBedrooms = housingTuple.get("numOfBedroom", Integer.class);
        Integer NumberOfBeds = housingTuple.get("numOfBeds", Integer.class);
        Integer NumberOfBathrooms = housingTuple.get("numOfBath", Integer.class);
        String houseAmenities = housingTuple.get("houseAmen", String.class);
        String adsTitle = housingTuple.get("adTitle", String.class);
        String description = housingTuple.get("Descriptions", String.class);
        Integer userID = housingTuple.get("userID", Integer.class);

        // Create a new HousingDTO object and set the values
        HousingDto housingDto = new HousingDto();
        housingDto.setId(id);
        housingDto.setPhoto_one(photoOne);
        housingDto.setPhoto_two(photoTwo);
        housingDto.setPhoto_three(photoThree);
        housingDto.setDate_created(createdDate);
        housingDto.setAddress(address);
        housingDto.setCountry(country);
        housingDto.setState(state);
        housingDto.setCity(city);
        housingDto.setZipcode(zipcode);
        housingDto.setNumber_of_travellers(NumberOfTravellers);
        housingDto.setNumber_of_bathrooms(NumberOfBathrooms);
        housingDto.setNumber_of_bedrooms(NumberOfBedrooms);
        housingDto.setNumber_of_beds(NumberOfBeds);
        housingDto.setHouse_amenities(houseAmenities);
        housingDto.setAd_title(adsTitle);
        housingDto.setDescription(description);
        housingDto.setUser_id(userID);

        return housingDto;
    }
}
