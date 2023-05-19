package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.checkNull.Helper;
import com.homeSwap.homeswapbackend.model.Apartment;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.apartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    apartmentService apartService;
    @PostMapping("/create")
    public ResponseEntity<apiResponse> createCategory(@RequestBody Apartment apartment) {
        if (Helper.notNull(apartService.readApartment(apartment.getApartmentName()))) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "apartment already exists"), HttpStatus.CONFLICT);
        }
        apartService.createApartment(apartment);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "created the apartment"), HttpStatus.CREATED);
    }

    public String createApartment(@RequestBody Apartment apartment)
    {
        apartService.createApartment(apartment);
        return "success";
    }

    @GetMapping("/list")
    public List<Apartment> getAllApartment()
    {
        return apartService.listApartment();

    }

    @GetMapping(value="/{Id}")
    public ResponseEntity<Apartment> getApartmentByID(@PathVariable(value = "Id") Integer id) {
        Apartment apartment = apartService.getApartmentById(id)
                .orElseThrow();
        return ResponseEntity.ok().body(apartment);
    }

    @PostMapping("/update/{ApartId}")
    public ResponseEntity<apiResponse> updateApartment(@PathVariable("ApartId") Integer ApartId, @RequestBody Apartment apartment) {
        // Check to see if the apartment exists.
        if (Helper.notNull(apartService.getApartmentById(ApartId))) {
            // If the category exists then update it.
            apartService.updateApartment(ApartId, apartment);
            return new ResponseEntity<apiResponse>(new apiResponse(true, "updated the apartment"), HttpStatus.OK);
        }

        // If the apartment doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<apiResponse>(new apiResponse(false, "apartment does not exist"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteApartment(@PathVariable("id") Integer id) {
        apartService.deleteApartment(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}
