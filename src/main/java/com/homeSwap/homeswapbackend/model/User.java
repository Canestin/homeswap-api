package com.homeSwap.homeswapbackend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String  email;
    private String password;
    private String phoneNumber;
    private String profilePicture;
    private String verificationCode;
    private boolean enabled;
    private String userRole;
    private String address;
    private String country;
    private String city;
    private String postalCode;

    //Testing to be deleted
    @OneToMany(mappedBy = "user",  cascade = CascadeType.REMOVE)
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.REMOVE)
    private Set<Rating> ratings;


    private Date createdDate;
/*
    //added new piece of code for address and it getter and setter take note
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Address address;

*/

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String phoneNumber, String profilePicture, String verificationCode,Boolean enabled, String userRole, String address, String country, String city, String postalCode, Date createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.verificationCode = verificationCode;
        this.enabled=enabled;
        this.userRole = userRole;
        this.address=address;
        this.country=country;
        this.city=city;
        this.postalCode=postalCode;
        this.createdDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }


    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
