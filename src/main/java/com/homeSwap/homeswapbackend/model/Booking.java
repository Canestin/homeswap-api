package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.BookingDTO;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number_Of_Travellers;
    private Integer number_Of_Pets;

    private Date checkIn_DateTime;
    private Date checkOut_DateTime;

    private Integer booking_Status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    housing house;

    private Date booking_date;

    public Booking() {
    }

    public Booking(BookingDTO bookingDTO, User user, housing house) {
        this.number_Of_Travellers=bookingDTO.getNumber_Of_Travellers();
        this.number_Of_Pets=bookingDTO.getNumber_Of_Pets();
        this.checkIn_DateTime = bookingDTO.getCheckIn_DateTime();
        this.checkOut_DateTime=bookingDTO.getCheckOut_DateTime();
        this.booking_Status=bookingDTO.getBooking_Status();
        this.user=user;
        this.house=house;
        this.booking_date = new Date();
    }

    public Booking(Integer number_Of_Travellers, Integer number_Of_Pets, Date checkIn_DateTime, Date checkOut_DateTime, Integer booking_Status, User user, housing house, Date booking_date) {
        this.number_Of_Travellers = number_Of_Travellers;
        this.number_Of_Pets = number_Of_Pets;
        this.checkIn_DateTime = checkIn_DateTime;
        this.checkOut_DateTime = checkOut_DateTime;
        this.booking_Status = booking_Status;
        this.user = user;
        this.house = house;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public housing getHouse() {
        return house;
    }

    public void setHouse(housing house) {
        this.house = house;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }
}
