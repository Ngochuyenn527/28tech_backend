package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toDTO(BuildingEntity entity) {
        return modelMapper.map(entity, BuildingDTO.class);
    }

    public BuildingEntity toEntity(BuildingDTO dto) {
        return modelMapper.map(dto, BuildingEntity.class);
    }
}
