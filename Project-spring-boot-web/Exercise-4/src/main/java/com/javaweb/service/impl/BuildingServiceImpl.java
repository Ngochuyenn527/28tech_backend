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
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.utils.*;

import javax.transaction.Transactional;
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
    private RentAreaRepository rentAreaRepository;

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
    public void deleteBuilding(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        rentAreaRepository.deleteByBuilding(building); // Xóa RentAreaEntity trước
        buildingRepository.delete(building); // Sau đó xóa BuildingEntity
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

    public static String removeAccent(List<String> typeCodes) {
        String s = String.join(",", typeCodes);
        return s;
    }

    private List<String> toTypeCodeList(String typeCode) {
        if (typeCode == null || typeCode.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(typeCode.split(","));
    }


    @Transactional
    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity;

        if (buildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Building not found!"));

            // Xóa RentArea cũ trước khi thêm mới
            rentAreaRepository.deleteByBuilding(buildingEntity);

            // Map nhưng giữ lại ID cũ
            modelMapper.map(buildingDTO, buildingEntity);
        } else {
            // Nếu thêm mới, tạo entity mới
            buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        }

        buildingEntity.setTypeCode(removeAccent(buildingDTO.getTypeCode()));

        // Lưu building trước để có ID
        buildingEntity = buildingRepository.save(buildingEntity);

        // Thêm RentArea nếu có
        if (StringUtils.checkString(buildingDTO.getRentArea())) {
            rentAreaService.addRentArea(buildingDTO);
        }

        return modelMapper.map(buildingEntity, BuildingDTO.class);
    }


    @Override
    public BuildingDTO findById(Long id) {
        if (id == null) {
            return null; // Nếu ID null thì không tìm
        }

        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        BuildingDTO res = modelMapper.map(buildingEntity, BuildingDTO.class);

        // Chuyển rentArea từ List<RentAreaEntity> thành chuỗi "100,200,300"
        String rentArea = buildingEntity.getRentAreaEntities().stream()
                .map(it -> it.getValue().toString())
                .collect(Collectors.joining(","));
        res.setRentArea(rentArea);
        res.setTypeCode(toTypeCodeList(buildingEntity.getTypeCode()));

        return res;
    }



    @Override
    public int countTotalItems(List<BuildingSearchResponse> list) {
        return (list != null) ? list.size() : 0;
    }


}
