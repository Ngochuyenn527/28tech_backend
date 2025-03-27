package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RentAreaServiceImpl implements RentAreaService {

    @Autowired
    private RentAreaRepository rentAreaRepository;

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
