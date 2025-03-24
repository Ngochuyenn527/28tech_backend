package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.utils.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private RentAreaService rentAreaService;
    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest) {

        List<String> typeCode = buildingSearchRequest.getTypeCode();

        //Chuyển dữ liệu tìm kiếm BuildingSearchRequest thành BuildingSearchBuilder
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest, typeCode);

        //Gọi Repository để lấy danh sách tòa nhà
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuildings(buildingSearchBuilder);

        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = buildingSearchResponseConverter.convertToBuildingSearchResponse(item);
            result.add(building);
        }
        return result;
    }

    @Override
    public void deleteBuildings(Long id) {
        rentAreaService.deleteByBuilding(id);
//        assignmentBuildingService.deleteByBuildingsIn(id);
        buildingRepository.deleteById(id);
    }


    public static boolean checkAddBuilding(BuildingDTO buildingDTO) {
        if (!StringUtils.checkString(buildingDTO.getName()) ||
                !StringUtils.checkString(buildingDTO.getStructure()) ||
                !StringUtils.checkString(buildingDTO.getDistrict()) ||
                !StringUtils.checkString(buildingDTO.getWard()) ||
                !StringUtils.checkString(buildingDTO.getStreet()) ||
                !StringUtils.checkString(buildingDTO.getDirection()) ||
                !StringUtils.checkString(buildingDTO.getRentArea()) ||
                !StringUtils.checkString(buildingDTO.getServiceFee()) ||
                !StringUtils.checkString(buildingDTO.getElectricityFee()) ||
                !StringUtils.checkString(buildingDTO.getWaterFee()) ||
                !StringUtils.checkString(buildingDTO.getDeposit()) ||
                !StringUtils.checkString(buildingDTO.getBrokerageFee()) ||

                !NumberUtils.checkNumber(buildingDTO.getNumberOfBasement()) ||
                !NumberUtils.checkNumber(buildingDTO.getRentPrice())) {
            return false;
        }
        return true;
    }

    public static String removeAccent(List<String> typeCodes){
        String s = String.join(",",typeCodes);
        return s;
    }

    private List<String> toTypeCodeList(String typeCode) {
        if (typeCode == null || typeCode.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(typeCode.split(","));
    }



    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(removeAccent(buildingDTO.getTypeCode()));

        buildingRepository.save(buildingEntity);

        if (StringUtils.checkString(buildingDTO.getRentArea())) {
            rentAreaService.addRentArea(buildingDTO);
        }

        return buildingDTO;
    }

    @Override
    public BuildingDTO findById(Long id) {
        // Tìm thực thể trong database theo ID
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();

        // Chuyển đổi từ BuildingEntity sang BuildingDTO
        BuildingDTO res = modelMapper.map(buildingEntity, BuildingDTO.class);

        // Lấy danh sách diện tích thuê
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntities();

        // Chuyển danh sách diện tích thuê thành chuỗi, ngăn cách bởi dấu ","
        String rentArea = rentAreaEntities.stream()
                .map(it -> it.getValue().toString())
                .collect(Collectors.joining(","));

        // Gán lại các giá trị vào DTO
        res.setRentArea(rentArea);
        res.setTypeCode(toTypeCodeList(buildingEntity.getTypeCode()));

        return res;
    }


    @Override
    public int countTotalItems(List<BuildingSearchResponse> list) {
        return (list != null) ? list.size() : 0;
    }


}
