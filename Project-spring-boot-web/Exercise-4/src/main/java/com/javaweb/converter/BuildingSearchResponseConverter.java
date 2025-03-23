package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResponseConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse convertToBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse res = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

        res.setAddress(buildingEntity.getWard() + ", " + buildingEntity.getStreet() + ", " + buildingEntity.getDistrict());

        List<RentAreaEntity> rentAreas = buildingEntity.getRentAreaEntities();
        String areaResult = rentAreas.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(","));
        res.setRentArea(areaResult);
        return res;
    }
}