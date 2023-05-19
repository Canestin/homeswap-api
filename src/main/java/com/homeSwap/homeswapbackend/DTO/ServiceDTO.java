package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Constraint;
import com.homeSwap.homeswapbackend.model.ServiceModel;

public class ServiceDTO {

    private Integer id;

    private String service_text;
    private Integer houseId;

    public ServiceDTO() {

    }

    public ServiceDTO(String service_text, Integer houseId) {
        this.service_text = service_text;
        this.houseId = houseId;
    }

    public  ServiceDTO(ServiceModel serviceModel)
    {
        this.setId(serviceModel.getId());
        this.setService_text(serviceModel.getService_text());
        this.setHouseId(serviceModel.getHouse().getId());
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

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
}
