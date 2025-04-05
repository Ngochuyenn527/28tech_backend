package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaConverter rentAreaConverter;

    //chuyển đổi typeCode được lưu ở DTO là dạng List thành String dưới DB
    public static String removeAccent(List<String> typeCodes) {
        String s = String.join(",", typeCodes);
        return Normalizer.normalize(s, Normalizer.Form.NFD) // Chuyển thành dạng decomposed
                .replaceAll("\\p{M}", ""); // Xóa các dấu (accents)
    }

    //chuyển đổi typeCode được lưu ở DB (be) là dạng String thành List ở DTO (fe)
    public List<String> toTypeCodeList(String typeCode) {
        if (typeCode == null || typeCode.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(typeCode.split(","));
    }

    //chuyển đổi BuildingEntity (be) thành BuildingDTO (fe) dùng modelMapper
    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setTypeCode(toTypeCodeList(buildingEntity.getTypeCode()));
        buildingDTO.setRentArea(rentAreaConverter.convertRentAreaEntityToString(buildingEntity.getRentAreaEntities()));
        return buildingDTO;
    }

    //chuyển đổi BuildingDTO (fe) thành BuildingEntity (be) dùng modelMapper
    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(removeAccent(buildingDTO.getTypeCode()));
        buildingEntity.setRentAreaEntities(rentAreaConverter.convertRentAreaStringToEntities(buildingDTO.getRentArea(), buildingEntity));
        return buildingEntity;
    }
}
