package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.ConstraintDTO;
import com.homeSwap.homeswapbackend.DTO.ServiceDTO;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.housingService;
import com.homeSwap.homeswapbackend.service.serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    serviceService service;

    @Autowired
    housingService houseService;

    @PostMapping("/add")
    public ResponseEntity<apiResponse> createConstraint(@RequestBody ServiceDTO serviceDTO) {
        Optional<housing> optionalHouse=houseService.getHouseById(serviceDTO.getHouseId());
        if (!optionalHouse.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "service is invalid"), HttpStatus.CONFLICT);
        }
        housing house = optionalHouse.get();
        service.addService(serviceDTO,house);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "new service successfully added"), HttpStatus.CREATED);
    }


    @GetMapping("/")

    public ResponseEntity<List<ServiceDTO>>getAllServices() {

        List<ServiceDTO> body = service.listService();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/update/{serviceID}")
    public ResponseEntity<apiResponse> updateConstraint(@PathVariable("serviceID") Integer serviceID, @RequestBody ServiceDTO serviceDTO) {
        Optional<housing> optionalHousing = houseService.getHouseById(serviceDTO.getHouseId());
        if (!optionalHousing.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "house is invalid"), HttpStatus.CONFLICT);
        }
        housing house = optionalHousing.get();
        service.updateService(serviceID, serviceDTO, house);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "service has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteService(@PathVariable("id") Integer id) {
        service.deleteService(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "service has been deleted"), HttpStatus.OK);
    }

    @GetMapping(value="/{Id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable(value = "Id") Integer id) {
        ServiceDTO serviceDTO = service.getServiceById2(id);
        return ResponseEntity.ok().body(serviceDTO);
    }

    //This would get all the constraints for a house
    @GetMapping(value="services/{houseID}")
    public List<ServiceDTO> getServicesByHouseID(@PathVariable(value = "houseID") Integer id) {
        Optional<housing> house = houseService.getHouseById(id);

        return service.getServicesByHouseId(house.get().getId());
    }


}
