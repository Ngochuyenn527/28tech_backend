package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.RentAreaService;
import com.javaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentAreaServiceImpl implements RentAreaService {

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingRepository buildingRepository;


    //    @Transactional
//    @Override
//    public void addRentArea(BuildingDTO buildingDTO) {
//        if (buildingDTO.getId() == null) {
//            throw new IllegalArgumentException("Building ID cannot be null");
//        }
//
//
//
//        // Lấy ra BuildingEntity từ DB
//        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId())
//                .orElseThrow(() -> new RuntimeException("Building not found"));
//
//        // Chuyển "100,200,300" => List<Integer>
//        List<RentAreaEntity> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
//                .map(String::trim)
//                .map(Long::parseLong)
//                .map(value -> {
//                    RentAreaEntity rentArea = new RentAreaEntity();
//                    rentArea.setValue(value);
//                    rentArea.setBuilding(buildingEntity);
//                    return rentArea;
//                })
//                .collect(Collectors.toList());
//
//        rentAreaRepository.saveAll(rentAreas);
//    }
    @Override
    public void addRentArea(BuildingEntity building) {
        if (building.getRentAreaEntities() != null && !building.getRentAreaEntities().isEmpty()) {
            // Gán BuildingEntity vào từng RentAreaEntity để đảm bảo mối quan hệ
            for (RentAreaEntity rentArea : building.getRentAreaEntities()) {
                rentArea.setBuilding(building);
            }
            // Lưu danh sách RentArea vào database
            rentAreaRepository.saveAll(building.getRentAreaEntities());
        }
    }
}
