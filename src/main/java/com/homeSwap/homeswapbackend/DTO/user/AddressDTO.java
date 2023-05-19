package com.homeSwap.homeswapbackend.DTO.user;

public class AddressDTO {

    private Integer id;

    private String street;
    private String country;
    private String state;
    private  String city;

    private String postal_code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public AddressDTO() {
    }

    public AddressDTO(String street,String country, String state, String city, String postal_code) {
        this.street=street;
        this.country = country;
        this.state = state;
        this.city = city;
        this.postal_code = postal_code;
    }
}
