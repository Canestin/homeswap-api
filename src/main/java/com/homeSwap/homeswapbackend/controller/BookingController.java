package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.BookingDTO;
import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.BookingService;
import com.homeSwap.homeswapbackend.service.UserService;
import com.homeSwap.homeswapbackend.service.housingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    housingService houseService;

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;


    @PostMapping("/add")
    public ResponseEntity<apiResponse> bookHouse(@RequestBody BookingDTO bookingDTO) {
        Optional<User> OptionalUser = userService.findUserById(bookingDTO.getUserId());
        Optional<housing> OptionalHouse = houseService.getHouseById(bookingDTO.getHousingId());

        if (!OptionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!OptionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
        User users=OptionalUser.get();
        housing house=OptionalHouse.get();

        bookingService.addBooking(bookingDTO, users, house);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "Booking request successfully sent"), HttpStatus.CREATED);
    }

    @GetMapping("/")

    public ResponseEntity<List<BookingDTO>>getAllBookings() {

        List<BookingDTO> bookingDTOS = bookingService.listAllBookingss();
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @PostMapping("/update/{bookingID}")
    public ResponseEntity<apiResponse> updateBookings(@PathVariable("bookingID") Integer bookingID, @RequestBody BookingDTO bookingDTO) {
        Optional<User> OptionalUser = userService.findUserById(bookingDTO.getUserId());
        Optional<housing> OptionalHouse = houseService.getHouseById(bookingDTO.getHousingId());

        if (!OptionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        if (!OptionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
        User users=OptionalUser.get();
        housing house=OptionalHouse.get();

        bookingService.updateBooking(bookingID, bookingDTO, users,house);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "booking has been updated"), HttpStatus.OK);
    }

    @GetMapping(value="/{Id}")
    public ResponseEntity<BookingDTO> getBookingsById(@PathVariable(value = "Id") Integer id) {
        BookingDTO bookingDTO = bookingService.getBookingsByID(id);
        return ResponseEntity.ok().body(bookingDTO);
    }

    //This would get all the bookings by house
    @GetMapping(value="books/{houseID}")
    public List<BookingDTO> getBookingsByHouseID(@PathVariable(value = "houseID") Integer id) {
        Optional<housing> house = houseService.getHouseById(id);

        return bookingService.getBookingsByHouseId(house.get().getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteBooking(@PathVariable("id") Integer id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "booking has been deleted"), HttpStatus.OK);
    }
}
