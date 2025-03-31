package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

//Custom dùng để chứa các phương thức không được thư viện JpaRepository hỗ trợ
public interface BuildingRepositoryCustom {
    List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder);

    int countTotalItem(BuildingSearchResponse buildingSearchResponse);

}
