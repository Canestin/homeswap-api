package com.homeSwap.homeswapbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeSwap.homeswapbackend.DTO.HousingDto;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "housing")
public class housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Integer user_id;

    // @ManyToOne(fetch = FetchType.LAZY, optional = true) // many housing of one
    // same apartment
    // @JoinColumn(name = "apartment_id", nullable = true)

    @OneToMany(mappedBy = "house", cascade = CascadeType.REMOVE)
    private Set<Constraint> constraints;

    @OneToMany(mappedBy = "house", cascade = CascadeType.REMOVE)
    private Set<ServiceModel> serviceModels;

    public housing() {
    }

    public housing(HousingDto housingDto) {
        this.photo_one = housingDto.getPhoto_one();
        this.photo_two = housingDto.getPhoto_two();
        this.photo_three = housingDto.getPhoto_three();
        this.date_created = new Date();
        this.address = housingDto.getAddress();
        this.country = housingDto.getCountry();
        this.state = housingDto.getState();
        this.city = housingDto.getCity();
        this.zipcode = housingDto.getZipcode();
        this.number_of_travellers = housingDto.getNumber_of_travellers();
        this.number_of_bathrooms = housingDto.getNumber_of_bathrooms();
        this.number_of_beds = housingDto.getNumber_of_beds();
        this.number_of_bedrooms = housingDto.getNumber_of_bedrooms();
        this.house_amenities = housingDto.getHouse_amenities();
        this.ad_title = housingDto.getAd_title();
        this.description = housingDto.getDescription();
        this.user_id = housingDto.getUser_id();
    }

    public housing(String photo_one, String photo_two, String photo_three, Date date_created, String address,
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
        this.number_of_travellers = number_of_travellers;
        this.number_of_bathrooms = number_of_bathrooms;
        this.number_of_beds = number_of_beds;
        this.number_of_bedrooms = number_of_bedrooms;
        this.house_amenities = house_amenities;
        this.ad_title = ad_title;
        this.description = description;
        this.user_id = user_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
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

    // DTO model that would be sent to the frontend for display

}
