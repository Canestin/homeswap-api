package com.homeSwap.homeswapbackend.model;

import com.homeSwap.homeswapbackend.DTO.ConstraintDTO;
import jakarta.persistence.*;

@Entity
@Table(name="houseConstraint")
public class Constraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String constraint_text;

    @ManyToOne(fetch = FetchType.LAZY,optional = false) //many housing of one same apartment
    @JoinColumn(name = "housing_id", nullable = false)
    private housing house;

    public Constraint() {
    }
    public Constraint(String constraint, housing house) {
        this.constraint_text = constraint;
        this.house = house;
    }

    public Constraint(ConstraintDTO constraintDTO, housing house)
    {
        this.setId(constraintDTO.getId());
        this.setConstraint(constraintDTO.getConstraint());
        this.house=house;
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



    public housing getHouse() {
        return house;
    }

    public void setHouse(housing house) {
        this.house = house;
    }



}
