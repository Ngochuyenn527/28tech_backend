package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    public List<RentAreaEntity> convertRentAreaStringToEntities(String rentAreaStr, BuildingEntity building) {
        List<RentAreaEntity> rentAreas = new ArrayList<>();
        String[] rentAreaValues = rentAreaStr.split(",");

        for (String area : rentAreaValues) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Long.parseLong(area.trim())); // Chuyển thành số nguyên
            rentAreaEntity.setBuilding(building); // Gán tòa nhà
            rentAreas.add(rentAreaEntity);
        }
        return rentAreas;
    }
}