package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
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


    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        return modelMapper.map(buildingEntity, BuildingDTO.class);
    }

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(removeAccent(buildingDTO.getTypeCode()));
        buildingEntity.setRentAreaEntities(rentAreaConverter.convertRentAreaStringToEntities(buildingDTO.getRentArea(), buildingEntity));
        return buildingEntity;
    }
}
