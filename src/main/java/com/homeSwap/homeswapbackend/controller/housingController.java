package com.homeSwap.homeswapbackend.controller;

import com.homeSwap.homeswapbackend.DTO.HousingDto;
import com.homeSwap.homeswapbackend.checkNull.Helper;
import com.homeSwap.homeswapbackend.model.User;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.response.apiResponse;
import com.homeSwap.homeswapbackend.service.UserService;
import com.homeSwap.homeswapbackend.service.housingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/housing")
public class housingController {

    @Autowired
    housingService houseService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<HousingDto>> getHousing() {

        List<HousingDto> body = houseService.listHousing();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> createHouse(@RequestBody HousingDto houseDto) {

        Optional<User> optionalUser = houseService.getUserById(houseDto.getUser_id());

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<apiResponse>(new apiResponse(false, "user is invalid"), HttpStatus.CONFLICT);
        }

        houseService.addHouse(houseDto);

        return new ResponseEntity<apiResponse>(new apiResponse(true, "created a new house"), HttpStatus.CREATED);
    }

    @PostMapping("/add-many")
    public ResponseEntity<apiResponse> createHouses(@RequestBody List<HousingDto> houseDtos) {
        for (HousingDto houseDto : houseDtos) {
            houseService.addHouse(houseDto);
        }

        return new ResponseEntity<apiResponse>(new apiResponse(true, "created multiple houses"), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{Id}")
    public HousingDto HouseByID(@PathVariable(value = "Id") Integer id) {
        return houseService.getHousesById(id);
    }

    @PostMapping("/upload-images")
    public String uploadImages(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
            @RequestParam("file3") MultipartFile file3) {

        ClassLoader classLoader = getClass().getClassLoader();
        String housesPath = new File(classLoader.getResource("static/images/houses/").getFile()).getAbsolutePath();

        MultipartFile[] files = { file1, file2, file3 };
        for (MultipartFile file : files) {

            try {
                String filePath = housesPath + File.separator + file.getOriginalFilename();
                System.out.println(filePath);
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                return "Image upload failed: " + e.getMessage();
            }
        }

        return "Images uploaded successfully.";
    }

    @PutMapping("/{houseID}")
    public ResponseEntity<apiResponse> updateHousing(@PathVariable("houseID") Integer houseID,
            @RequestBody HousingDto housingDto) {

        houseService.updateHouse(houseID, housingDto);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "housing has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> DeleteHousing(@PathVariable("id") Integer id) {
        houseService.deleteHouse(id);
        return new ResponseEntity<apiResponse>(new apiResponse(true, "house has been deleted"), HttpStatus.OK);
    }

    // This would get all the housings by user
    @GetMapping(value = "house/{userID}")
    public List<HousingDto> getHousingsByHouseID(@PathVariable(value = "userID") Integer id) {
        Optional<User> user = userService.findUserById(id);

        return houseService.getHousingsByUId(user.get().getId());
    }

}
