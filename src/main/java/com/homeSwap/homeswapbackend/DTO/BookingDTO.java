package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Booking;
import com.homeSwap.homeswapbackend.model.Rating;

import java.util.Date;

public class BookingDTO {

    private Integer id;

    private Integer number_Of_Travellers;
    private Integer number_Of_Pets;

    private Date checkIn_DateTime;
    private Date checkOut_DateTime;

    private Integer booking_Status;

    private Integer housingId;
    private Integer userId;

    private Date booking_date;

    public BookingDTO() {
    }

    public BookingDTO(Booking booking)
    {
        this.setId(booking.getId());
        this.setCheckIn_DateTime(booking.getCheckIn_DateTime());
        this.setCheckOut_DateTime(booking.getCheckOut_DateTime());
        this.setNumber_Of_Travellers(booking.getNumber_Of_Travellers());
        this.setNumber_Of_Pets(booking.getNumber_Of_Pets());
        this.setBooking_Status(booking.getBooking_Status());
        this.setUserId(booking.getUser().getId());
        this.setHousingId(booking.getHouse().getId());
        this.setBooking_date(booking.getBooking_date());
    }


    public BookingDTO(Integer number_Of_Travellers, Integer number_Of_Pets, Date checkIn_DateTime, Date checkOut_DateTime, Integer booking_Status, Integer housingId, Integer userId, Date booking_date) {
        this.number_Of_Travellers = number_Of_Travellers;
        this.number_Of_Pets = number_Of_Pets;
        this.checkIn_DateTime = checkIn_DateTime;
        this.checkOut_DateTime = checkOut_DateTime;
        this.booking_Status = booking_Status;
        this.housingId = housingId;
        this.userId = userId;
        this.booking_date = booking_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber_Of_Travellers() {
        return number_Of_Travellers;
    }

    public void setNumber_Of_Travellers(Integer number_Of_Travellers) {
        this.number_Of_Travellers = number_Of_Travellers;
    }

    public Integer getNumber_Of_Pets() {
        return number_Of_Pets;
    }

    public void setNumber_Of_Pets(Integer number_Of_Pets) {
        this.number_Of_Pets = number_Of_Pets;
    }

    public Date getCheckIn_DateTime() {
        return checkIn_DateTime;
    }

    public void setCheckIn_DateTime(Date checkIn_DateTime) {
        this.checkIn_DateTime = checkIn_DateTime;
    }

    public Date getCheckOut_DateTime() {
        return checkOut_DateTime;
    }

    public void setCheckOut_DateTime(Date checkOut_DateTime) {
        this.checkOut_DateTime = checkOut_DateTime;
    }

    public Integer getBooking_Status() {
        return booking_Status;
    }

    public void setBooking_Status(Integer booking_Status) {
        this.booking_Status = booking_Status;
    }

    public Integer getHousingId() {
        return housingId;
    }

    public void setHousingId(Integer housingId) {
        this.housingId = housingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }
}
