package com.homeSwap.homeswapbackend.DTO.user;

import com.homeSwap.homeswapbackend.model.User;

import java.util.Date;

public class SignUpDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
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
    private Date createdDate;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public SignUpDTO(String firstName, String lastName, String email, String password, String phoneNumber, String profilePicture, String verificationCode, Boolean enabled, String userRole, String address, String country, String city, String postalCode, Date createdDate) {
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

    public SignUpDTO() {
    }

    public SignUpDTO(User user){
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getFirstName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setProfilePicture(user.getProfilePicture());
        this.setVerificationCode(user.getVerificationCode());
        this.setEnabled(user.isEnabled());
        this.setUserRole(user.getUserRole());
        this.setAddress(user.getAddress());
        this.setCountry(user.getCountry());
        this.setCity(user.getCity());
        this.setPostalCode(user.getPostalCode());
        this.setCreatedDate(user.getCreatedDate());
    }

}
