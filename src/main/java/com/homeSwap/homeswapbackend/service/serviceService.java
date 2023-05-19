package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.ConstraintDTO;
import com.homeSwap.homeswapbackend.DTO.ServiceDTO;
import com.homeSwap.homeswapbackend.model.Constraint;
import com.homeSwap.homeswapbackend.model.ServiceModel;
import com.homeSwap.homeswapbackend.model.housing;
import com.homeSwap.homeswapbackend.repository.ServiceRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class serviceService {

    @Autowired
    ServiceRepository serviceRepository;

    public ServiceDTO getDtoFromService(ServiceModel serviceModel) {


        ServiceDTO serviceDTO = new ServiceDTO(serviceModel);
        return serviceDTO;
    }
    public static ServiceModel getServiceFromDto(ServiceDTO serviceDTO, housing house) {
        ServiceModel serviceModel= new ServiceModel(serviceDTO, house);
        return serviceModel;
    }

    public void addService(ServiceDTO serviceDTO, housing house) {
        ServiceModel serviceModel = getServiceFromDto(serviceDTO, house);
        serviceRepository.save(serviceModel);
    }

    public List<ServiceDTO> listService() {
        List<ServiceModel> serviceModels = serviceRepository.findAll();
        List<ServiceDTO> serviceDTOS= new ArrayList<>();
        for (ServiceModel serviceModel : serviceModels) {

            serviceDTOS.add(getDtoFromService(serviceModel));
        }
        return serviceDTOS;

    }

    public void deleteService(Integer serviceID){
        serviceRepository.deleteById(serviceID);

    }

    public void updateService(Integer serviceID, ServiceDTO serviceDTO, housing house) {
        ServiceModel serviceModel = getServiceFromDto(serviceDTO, house);
        serviceModel.setId(serviceID);
        serviceRepository.save(serviceModel);
    }

    public ServiceDTO getServiceById2(Integer id) {
        ServiceModel serviceModel = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("housing not found"));
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(serviceModel.getId());
        serviceDTO.setService_text(serviceModel.getService_text());
        serviceDTO.setHouseId(serviceModel.getHouse().getId());

        return serviceDTO;
    }

    public List<ServiceDTO> getServicesByHouseId(Integer houseID){

        //return ratingRepository.getRatingsByHouseID(houseID);
        List<Tuple> service = serviceRepository.getServiceByHouseID(houseID);
        return service.stream()
                .map(this::mapServiceTupleToDTO)
                .collect(Collectors.toList());

    }

    //This method extracts the values of the tuple using the get() method and sets them in a new RatingDTO object
    private ServiceDTO mapServiceTupleToDTO(Tuple serviceTuple) {
        Integer id = serviceTuple.get("id", Integer.class);
        String serviceText = serviceTuple.get("serveText", String.class);
        Integer houseID=serviceTuple.get("houseID", Integer.class);


        // Create a new RatingDTO object and set the values
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(id);
        serviceDTO.setService_text(serviceText);
        serviceDTO.setHouseId(houseID);

        return serviceDTO;
    }

}
