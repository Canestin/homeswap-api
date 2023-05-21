package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.ConstraintDTO;
import com.homeSwap.homeswapbackend.DTO.HousingDto;
import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.Constraint;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.repository.ConstraintRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class constraintService {

    @Autowired
    ConstraintRepository constraintRepo;

    public ConstraintDTO getDtoFromConstraint(Constraint constraint) {

        ConstraintDTO constraintDTO = new ConstraintDTO(constraint);
        return constraintDTO;
    }

    public static Constraint getConstraintFromDto(ConstraintDTO constraintDto, housing house) {
        Constraint con = new Constraint(constraintDto, house);
        return con;
    }

    public void addConstraint(ConstraintDTO constraintDTO, housing house) {
        Constraint con = getConstraintFromDto(constraintDTO, house);
        constraintRepo.save(con);
    }

    public List<ConstraintDTO> listConstraint() {
        List<Constraint> constraints = constraintRepo.findAll();
        List<ConstraintDTO> constraintDtoss = new ArrayList<>();
        for (Constraint con : constraints) {

            constraintDtoss.add(getDtoFromConstraint(con));
        }
        return constraintDtoss;

    }

    public void deleteConstraint(Integer constraintID) {
        constraintRepo.deleteById(constraintID);

    }

    public void updateConstraint(Integer constraintID, ConstraintDTO constraintDTO, housing house) {
        Constraint constraint = getConstraintFromDto(constraintDTO, house);
        constraint.setId(constraintID);
        constraintRepo.save(constraint);
    }

    public Optional<Constraint> getConstraintById(Integer constraintID) {
        return constraintRepo.findById(constraintID);
    }

    // Getting constraints by id
    public ConstraintDTO getConstraintById2(Integer id) {
        Constraint constraint = constraintRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("housing not found"));
        ConstraintDTO con = new ConstraintDTO();
        con.setId(constraint.getId());
        con.setConstraint_text(constraint.getConstraint());
        con.setHouseId(constraint.getHouse().getId());

        return con;
    }

    public List<ConstraintDTO> getConstrainsByHouseId(Integer houseID) {

        // return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> constraints = constraintRepo.getConstraintsByHouseID(houseID);
        return constraints.stream()
                .map(this::mapRatingTupleToDTO)
                .collect(Collectors.toList());

    }

    // This method extracts the values of the tuple using the get() method and sets
    // them in a new RatingDTO object
    private ConstraintDTO mapRatingTupleToDTO(Tuple ratingTuple) {
        Integer id = ratingTuple.get("id", Integer.class);
        String constraintText = ratingTuple.get("conText", String.class);
        Integer houseID = ratingTuple.get("houseID", Integer.class);

        // Create a new RatingDTO object and set the values
        ConstraintDTO constraintDTO = new ConstraintDTO();
        constraintDTO.setId(id);
        constraintDTO.setConstraint_text(constraintText);
        constraintDTO.setHouseId(houseID);

        return constraintDTO;
    }

}
