package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.ConstraintDTO;
import com.homeSwap.homeswapbackend.DTO.ServiceDTO;
import jakarta.persistence.*;

@Entity
@Table(name="service")
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String service_text;

    @ManyToOne(fetch = FetchType.LAZY,optional = false) //many housing of one same apartment
    @JoinColumn(name = "housing_id", nullable = false)
    private housing house;

    public ServiceModel() {
    }

    public ServiceModel(String service_text, housing house) {
        this.service_text = service_text;
        this.house = house;
    }

    public ServiceModel(ServiceDTO serviceDTO, housing house)
    {
        this.setId(serviceDTO.getId());
        this.setService_text(serviceDTO.getService_text());
        this.house=house;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService_text() {
        return service_text;
    }

    public void setService_text(String service_text) {
        this.service_text = service_text;
    }

    public housing getHouse() {
        return house;
    }

    public void setHouse(housing house) {
        this.house = house;
    }
}
