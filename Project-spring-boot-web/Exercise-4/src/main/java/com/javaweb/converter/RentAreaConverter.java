package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//được sử dụng khi thêm mới hoặc sửa tòa nhà (khi đó sẽ cần chuyển đổi kiểu dữ liệu của renarea)
//BuildingDTO chứa 1 dãy String rentArea do người dùng nhập
//Nhưng khi lưu xuống database, buildingEntity lại chứa 1 List rentArea (quan hệ 1:N)
@Component
public class RentAreaConverter {
    //chuyển đổi rentarea được lưu dưới kiểu String bên dto thành rentarea được lưu dưới dạng List bên entity để lưu vào database
    public List<RentAreaEntity> convertRentAreaStringToEntities(String rentAreaStr, BuildingEntity buildingEntity) {
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
        String[] rentAreaValues = rentAreaStr.split(",");

        for (String area : rentAreaValues) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Long.parseLong(area.trim())); // Chuyển thành số nguyên
            rentAreaEntity.setBuilding(buildingEntity); // Gán tòa nhà
            rentAreaEntityList.add(rentAreaEntity);
        }
        return rentAreaEntityList;
    }

    // Chuyển rentAreaEntities thành chuỗi
    public String convertRentAreaEntityToString(List<RentAreaEntity> rentAreas) {
        return rentAreas.stream()
                .map(ra -> String.valueOf(ra.getValue()))  // Lấy giá trị diện tích
                .collect(Collectors.joining(","));
    }
}