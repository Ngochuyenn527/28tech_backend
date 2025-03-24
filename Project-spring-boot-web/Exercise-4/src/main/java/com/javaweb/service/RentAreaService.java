package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;

public interface RentAreaService {
    public void addRentArea(BuildingDTO  buildingDTO);

    public void deleteByBuilding(Long id);
}
