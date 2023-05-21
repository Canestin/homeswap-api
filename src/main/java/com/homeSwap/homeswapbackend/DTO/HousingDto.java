package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.housing;

import java.util.Date;

public class HousingDto {

    private Integer id;
    private String photo_one;
    private String photo_two;
    private String photo_three;
    private Date date_created;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private Integer number_of_travellers;
    private Integer number_of_bedrooms;
    private Integer number_of_beds;
    private Integer number_of_bathrooms;
    private String house_amenities;
    private String ad_title;
    private String description;
    private int user_id;

    public HousingDto() {
    }

    public HousingDto(housing house) {
        this.setId(house.getId());
        this.setPhoto_one(house.getPhoto_one());
        this.setPhoto_two(house.getPhoto_two());
        this.setPhoto_three(house.getPhoto_three());
        this.setDate_created(house.getDate_created());
        this.setAddress(house.getAddress());
        this.setCountry(house.getCountry());
        this.setState(house.getState());
        this.setCity(house.getCity());
        this.setZipcode(house.getZipcode());
        this.setNumber_of_travellers(house.getNumber_of_travellers());
        this.setNumber_of_bathrooms(house.getNumber_of_bathrooms());
        this.setNumber_of_bedrooms(house.getNumber_of_bedrooms());
        this.setNumber_of_beds(house.getNumber_of_beds());
        this.setHouse_amenities(house.getHouse_amenities());
        this.setAd_title(house.getAd_title());
        this.setDescription(house.getDescription());
        this.setUser_id(house.getUser_id());

    }

    public HousingDto(String photo_one, String photo_two, String photo_three, Date date_created, String address,
            String country, String state, String city, String zipcode, String description, Integer number_of_travellers,
            Integer number_of_bedrooms, Integer number_of_beds, Integer number_of_bathrooms, String house_amenities,
            String ad_title, int user_id) {
        this.photo_one = photo_one;
        this.photo_two = photo_two;
        this.photo_three = photo_three;
        this.date_created = date_created;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.description = description;
        this.number_of_travellers = number_of_travellers;
        this.number_of_bathrooms = number_of_bathrooms;
        this.number_of_beds = number_of_beds;
        this.number_of_bedrooms = number_of_bedrooms;
        this.house_amenities = house_amenities;
        this.ad_title = ad_title;
        this.user_id = user_id;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto_one() {
        return photo_one;
    }

    public void setPhoto_one(String photo_one) {
        this.photo_one = photo_one;
    }

    public String getPhoto_two() {
        return photo_two;
    }

    public void setPhoto_two(String photo_two) {
        this.photo_two = photo_two;
    }

    public String getPhoto_three() {
        return photo_three;
    }

    public void setPhoto_three(String photo_three) {
        this.photo_three = photo_three;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getNumber_of_travellers() {
        return number_of_travellers;
    }

    public void setNumber_of_travellers(Integer number_of_travellers) {
        this.number_of_travellers = number_of_travellers;
    }

    public Integer getNumber_of_bedrooms() {
        return number_of_bedrooms;
    }

    public void setNumber_of_bedrooms(Integer number_of_bedrooms) {
        this.number_of_bedrooms = number_of_bedrooms;
    }

    public Integer getNumber_of_beds() {
        return number_of_beds;
    }

    public void setNumber_of_beds(Integer number_of_beds) {
        this.number_of_beds = number_of_beds;
    }

    public Integer getNumber_of_bathrooms() {
        return number_of_bathrooms;
    }

    public void setNumber_of_bathrooms(Integer number_of_bathrooms) {
        this.number_of_bathrooms = number_of_bathrooms;
    }

    public String getHouse_amenities() {
        return house_amenities;
    }

    public void setHouse_amenities(String house_amenities) {
        this.house_amenities = house_amenities;
    }

    public String getAd_title() {
        return ad_title;
    }

    public void setAd_title(String ad_title) {
        this.ad_title = ad_title;
    }
}
