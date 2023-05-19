package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.BookingDTO;
import com.homeSwap.homeswapbackend.DTO.HousingDto;
import com.homeSwap.homeswapbackend.checkNull.Helper;
import com.homeSwap.homeswapbackend.model.Apartment;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.UserService;
import com.homeSwap.homeswapbackend.service.apartmentService;
import com.homeSwap.homeswapbackend.service.housingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.homeSwap.homeswapbackend.service.apartmentService.*;

@RestController
@RequestMapping("/housing")
public class housingController {

    @Autowired
    housingService houseService;
    @Autowired
    apartmentService appService;

    @Autowired
    UserService userService;


    @GetMapping("/")

    public ResponseEntity<List<HousingDto>>getHousing() {

            List<HousingDto> body = houseService.listHousing();
            return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> createHouse(@RequestBody HousingDto houseDto) {
         Optional<Apartment> optionalApartment=appService.getApartmentById(houseDto.getApartmentId());
        Optional<User> optionalUser = houseService.getUserById(houseDto.getUser_id());

        if (!optionalApartment.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "apartment is invalid"), HttpStatus.CONFLICT);
        }

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }



        Apartment apart = optionalApartment.get();

        houseService.addHouse(houseDto, apart);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "created a new house"), HttpStatus.CREATED);
    }









    /*

    @GetMapping(value="/{Id}")
    public HousingDto HouseByID(@PathVariable(value = "Id") Integer id) {
        return houseService.getHousesById(id);
    }
*/

    //testing
    @GetMapping(value="/{Id}")
    public ResponseEntity<HousingDto> getApartmentByID(@PathVariable(value = "Id") Integer id) {
        HousingDto house = houseService.getHousesById(id);
        return ResponseEntity.ok().body(house);
    }


    @PostMapping("/update/{houseID}")
    public ResponseEntity<apiResponse> updateHousing(@PathVariable("houseID") Integer houseID, @RequestBody HousingDto housingDto) {
        Optional<Apartment> optionalApartment = appService.getApartmentById(housingDto.getApartmentId());
        if (!optionalApartment.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "apartment is invalid"), HttpStatus.CONFLICT);
        }
        Apartment apartment = optionalApartment.get();
        houseService.updateHouse(houseID, housingDto, apartment);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "housing has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteHousing(@PathVariable("id") Integer id) {
        houseService.deleteHouse(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "house has been deleted"), HttpStatus.OK);
    }

    //This would get all the housings by user
    @GetMapping(value="house/{userID}")
    public List<HousingDto> getHousingsByHouseID(@PathVariable(value = "userID") Integer id) {
        Optional<User> user = userService.findUserById(id);

        return houseService.getHousingsByUId(user.get().getId());
    }

}
