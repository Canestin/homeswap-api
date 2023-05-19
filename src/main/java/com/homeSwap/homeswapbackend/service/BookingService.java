package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.BookingDTO;
import com.homeSwap.homeswapbackend.model.*;
import com.homeSwap.homeswapbackend.repository.BookingRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;


    public static Booking getBookingFromDto(BookingDTO bookingDTO, User user, housing house) {
        Booking booking = new Booking(bookingDTO, user, house);
        return booking;
    }

    public BookingDTO getDtoFromBooking(Booking booking) {


        BookingDTO bookingDTO = new BookingDTO(booking);
        return bookingDTO;
    }

    public void addBooking(BookingDTO bookingDTO, User user, housing house) {
        Booking booking = getBookingFromDto(bookingDTO, user, house);
        bookingRepository.save(booking);
    }

    public List<BookingDTO> listAllBookingss() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> bookingDTOS= new ArrayList<>();
        for (Booking book : bookings) {

            bookingDTOS.add(getDtoFromBooking(book));
        }
        return bookingDTOS;

    }

    public void updateBooking(Integer bookingID, BookingDTO bookingDTO, User user, housing house) {
        Booking booking = getBookingFromDto(bookingDTO, user, house);
        booking.setId(bookingID);
        bookingRepository.save(booking);
    }

    public BookingDTO getBookingsByID(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("bookings not found"));

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setNumber_Of_Travellers(booking.getNumber_Of_Travellers());
        bookingDTO.setNumber_Of_Pets(booking.getNumber_Of_Pets());
        bookingDTO.setBooking_Status(booking.getBooking_Status());
        bookingDTO.setCheckIn_DateTime(booking.getCheckIn_DateTime());
        bookingDTO.setCheckOut_DateTime(booking.getCheckOut_DateTime());
        bookingDTO.setBooking_date(booking.getBooking_date());

        bookingDTO.setHousingId(booking.getHouse().getId());
        bookingDTO.setUserId(booking.getUser().getId());;

        return bookingDTO;
    }

    public List<BookingDTO> getBookingsByHouseId(Integer houseID){

        //return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> bookings = bookingRepository.getBookingsByHouseID(houseID);
        return bookings.stream()
                .map(this::mapBookingTupleToDTO)
                .collect(Collectors.toList());

    }

    //This method extracts the values of the tuple using the get() method and sets them in a new RatingDTO object
    private BookingDTO mapBookingTupleToDTO(Tuple ratingTuple) {
        Integer id = ratingTuple.get("id", Integer.class);
        Integer Number_Of_Travellers = ratingTuple.get("numTravel", Integer.class);
        Integer NumberPets = ratingTuple.get("numPets", Integer.class);
        Integer Booking_Status = ratingTuple.get("bookStatus", Integer.class);
        Date checkInTime = ratingTuple.get("CheckInDate", Date.class);
        Date checkOutTime = ratingTuple.get("checkOutDate", Date.class);
        Date setBooking_date = ratingTuple.get("bookDate", Date.class);
        Integer HousingId = ratingTuple.get("userid", Integer.class);
        Integer userID = ratingTuple.get("houseID", Integer.class);

        // Create a new RatingDTO object and set the values
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(id);
        bookingDTO.setNumber_Of_Travellers(Number_Of_Travellers);
        bookingDTO.setNumber_Of_Pets(NumberPets);
        bookingDTO.setBooking_Status(Booking_Status);
        bookingDTO.setCheckIn_DateTime(checkInTime);
        bookingDTO.setCheckOut_DateTime(checkOutTime);
        bookingDTO.setBooking_date(setBooking_date);
        bookingDTO.setUserId(userID);
        bookingDTO.setHousingId(HousingId);

        return bookingDTO;
    }

    public void deleteBooking(Integer bookingID){
        bookingRepository.deleteById(bookingID);

    }
}
