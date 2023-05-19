package com.homeSwap.homeswapbackend.DTO;

import com.homeSwap.homeswapbackend.model.Constraint;

import java.util.Date;

public class ConstraintDTO {

    private Integer id;



    private String constraint_text;
    private Integer houseId;

    public String getConstraint_text() {
        return constraint_text;
    }

    public void setConstraint_text(String constraint_text) {
        this.constraint_text = constraint_text;
    }

    public ConstraintDTO() {

    }


    public ConstraintDTO(String constraint, Integer houseId) {
        this.constraint_text = constraint;
        this.houseId = houseId;
    }

    public  ConstraintDTO(Constraint constraint)
    {
        this.setId(constraint.getId());
        this.setConstraint(constraint.getConstraint());
        this.setHouseId(constraint.getHouse().getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstraint() {
        return constraint_text;
    }

    public void setConstraint(String constraint) {
        this.constraint_text = constraint;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }


}
