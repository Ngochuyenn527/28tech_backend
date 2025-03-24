package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentAreaServiceImpl implements RentAreaService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        // Tìm BuildingEntity dựa trên ID của DTO
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();

        // Xóa tất cả diện tích thuê cũ của tòa nhà
        rentAreaRepository.deleteByBuilding(buildingEntity);

        // Tách chuỗi diện tích thuê thành mảng
        String[] rentAreas = buildingDTO.getRentArea().trim().split(",");

        // Lặp qua từng diện tích và lưu vào DB
        for (String val : rentAreas) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(buildingDTO, Long.valueOf(val));
            rentAreaRepository.save(rentAreaEntity);
        }
    }


    @Override
    public void deleteByBuilding(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        // Xóa tất cả diện tích thuê cũ của tòa nhà
        rentAreaRepository.deleteByBuilding(buildingEntity);
    }

}
