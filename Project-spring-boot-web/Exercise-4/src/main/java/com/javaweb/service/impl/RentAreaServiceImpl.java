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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        if (buildingDTO.getId() == null) {
            throw new IllegalArgumentException("Building ID cannot be null");
        }

        // Tìm BuildingEntity theo ID
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId())
                .orElseThrow(() -> new RuntimeException("Building not found with ID: " + buildingDTO.getId()));

        // Xóa tất cả diện tích thuê cũ của tòa nhà
        rentAreaRepository.deleteByBuilding(buildingEntity);

        // Kiểm tra rentArea có hợp lệ không
        if (buildingDTO.getRentArea() == null || buildingDTO.getRentArea().trim().isEmpty()) {
            return; // Không có diện tích thuê mới, chỉ xóa diện tích cũ
        }

        // Tách chuỗi diện tích thuê thành danh sách số nguyên (bỏ khoảng trắng, kiểm tra lỗi)
        List<RentAreaEntity> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(String::trim)
                .filter(s -> {
                    if (s.isEmpty()) return false;
                    try {
                        Long.parseLong(s);
                        return true;
                    } catch (NumberFormatException e) {
                        System.err.println("⚠️ Warning: Skipping invalid rent area value: " + s);
                        return false;
                    }
                })
                .map(s -> rentAreaConverter.toRentAreaEntity(buildingDTO, Long.valueOf(s)))
                .collect(Collectors.toList());

        // Lưu toàn bộ danh sách diện tích thuê một lần để tối ưu hiệu suất
        rentAreaRepository.saveAll(rentAreas);
    }



    @Override
    public void deleteByBuilding(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with ID: " + id));

        // Kiểm tra trước khi xóa
        if (!rentAreaRepository.existsByBuilding(buildingEntity)) {
            System.out.println("⚠️ Warning: No rent areas found for building ID: " + id);
            return;
        }

        rentAreaRepository.deleteByBuilding(buildingEntity);
    }



}
