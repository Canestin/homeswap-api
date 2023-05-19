package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.ConstraintDTO;
import com.homeSwap.homeswapbackend.DTO.RatingDTO;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.constraintService;
import com.homeSwap.homeswapbackend.service.housingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/constraint")
public class constraintController {

    @Autowired
    constraintService conService;
    @Autowired
    housingService houseService;

    @PostMapping("/add")
    public ResponseEntity<apiResponse> createConstraint(@RequestBody ConstraintDTO constraintDTO) {
        Optional<housing> optionalHouse=houseService.getHouseById(constraintDTO.getHouseId());
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "constraint is invalid"), HttpStatus.CONFLICT);
        }
        housing house = optionalHouse.get();
        conService.addConstraint(constraintDTO,house);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "new constraints successfully added"), HttpStatus.CREATED);
    }

    @GetMapping("/")

    public ResponseEntity<List<ConstraintDTO>>getAllConstraints() {

        List<ConstraintDTO> body = conService.listConstraint();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/update/{constraintID}")
    public ResponseEntity<apiResponse> updateConstraint(@PathVariable("constraintID") Integer constraintID, @RequestBody ConstraintDTO constraintDTO) {
        Optional<housing> optionalHousing = houseService.getHouseById(constraintDTO.getHouseId());
        if (!optionalHousing.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
        housing house = optionalHousing.get();
        conService.updateConstraint(constraintID, constraintDTO, house);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "constraint has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteConstraint(@PathVariable("id") Integer id) {
        conService.deleteConstraint(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "constraint has been deleted"), HttpStatus.OK);
    }

    @GetMapping(value="/{Id}")
    public ResponseEntity<ConstraintDTO> getConstraintByID(@PathVariable(value = "Id") Integer id) {
        ConstraintDTO con = conService.getConstraintById2(id);
        return ResponseEntity.ok().body(con);
    }


    //This would get all the constraints for a house
    @GetMapping(value="constraints/{houseID}")
    public List<ConstraintDTO> getConstraintsByHouseID(@PathVariable(value = "houseID") Integer id) {
        Optional<housing> house = houseService.getHouseById(id);

        return conService.getConstrainsByHouseId(house.get().getId());
    }



}
