package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private RentAreaConverter rentAreaConverter;

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
    public int countTotalItems(List<BuildingSearchResponse> list) {
        return (list != null) ? list.size() : 0;
    }


//    public static boolean checkValueAdd(BuildingDTO buildingDTO) {
//        if (!StringUtils.checkString(buildingDTO.getName()) ||
//                !StringUtils.checkString(buildingDTO.getStructure()) ||
//                !StringUtils.checkString(buildingDTO.getDistrict()) ||
//                !StringUtils.checkString(buildingDTO.getWard()) ||
//                !StringUtils.checkString(buildingDTO.getStreet()) ||
//                !StringUtils.checkString(buildingDTO.getDirection()) ||
//                !StringUtils.checkString(buildingDTO.getRentArea()) ||
//                !StringUtils.checkString(buildingDTO.getServiceFee()) ||
//                !StringUtils.checkString(buildingDTO.getElectricityFee()) ||
//                !StringUtils.checkString(buildingDTO.getWaterFee()) ||
//                !StringUtils.checkString(buildingDTO.getDeposit()) ||
//                !StringUtils.checkString(buildingDTO.getBrokerageFee()) ||
//
//                !NumberUtils.checkNumber(buildingDTO.getNumberOfBasement()) ||
//                !NumberUtils.checkNumber(buildingDTO.getRentPrice())) {
//            return false;
//        }
//        return true;
//    }

    public static String removeAccent(List<String> typeCodes) {
        String s = String.join(",", typeCodes);
        return Normalizer.normalize(s, Normalizer.Form.NFD) // Chuyển thành dạng decomposed
                .replaceAll("\\p{M}", ""); // Xóa các dấu (accents)
    }

    private List<String> toTypeCodeList(String typeCode) {
        if (typeCode == null || typeCode.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(typeCode.split(","));
    }

    private String convertRentAreasToString(List<RentAreaEntity> rentAreas) {
        return rentAreas.stream()
                .map(ra -> String.valueOf(ra.getValue()))  // Lấy giá trị diện tích
                .collect(Collectors.joining(","));
    }

    @Override
    public BuildingDTO addBuilding(@Valid  BuildingDTO buildingDTO) {

        // ✅ Chuyển từ DTO -> Entity
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);

        // ✅ Lưu tòa nhà vào database để lấy ID (nếu là thêm mới)
        buildingEntity = buildingRepository.save(buildingEntity);

        // ✅ Xử lý danh sách diện tích thuê (RentArea)
        if (StringUtils.checkString(buildingDTO.getRentArea())) {
            // Xóa RentArea cũ để tránh trùng lặp
            rentAreaRepository.deleteByBuilding(buildingEntity.getId());

            // Chuyển chuỗi "100,200,300" thành danh sách RentAreaEntity
            List<RentAreaEntity> newRentAreas = rentAreaConverter.convertRentAreaStringToEntities(buildingDTO.getRentArea(), buildingEntity);

            // Gán danh sách mới cho Building
            buildingEntity.setRentAreaEntities(newRentAreas);

            // Thêm mới vào database
            rentAreaService.addRentArea(buildingEntity);
        }

        // ✅ Lưu lại sau khi cập nhật danh sách RentArea
        BuildingEntity savedBuilding = buildingRepository.save(buildingEntity);

        // ✅ Ánh xạ lại từ Entity -> DTO để trả về response
        BuildingDTO savedBuildingDTO = modelMapper.map(savedBuilding, BuildingDTO.class);

        // ✅ Chuyển danh sách RentAreaEntity -> String để hiển thị đúng trong API
        savedBuildingDTO.setRentArea(convertRentAreasToString(savedBuilding.getRentAreaEntities()));

        return savedBuildingDTO;
    }


    @Override
    public BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO) {
        // Kiểm tra tòa nhà có tồn tại không
        BuildingEntity existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));

        // Ánh xạ DTO -> Entity (tránh thay đổi ID)
        modelMapper.map(buildingDTO, existingBuilding);
        existingBuilding.setId(id); // Giữ nguyên ID

        // Xóa RentArea cũ
        rentAreaRepository.deleteByBuilding(existingBuilding.getId());

        // Cập nhật danh sách RentArea từ DTO (tách chuỗi thành danh sách số)
        if (StringUtils.checkString(buildingDTO.getRentArea())) {
            List<RentAreaEntity> newRentAreas = rentAreaConverter.convertRentAreaStringToEntities(buildingDTO.getRentArea(), existingBuilding);
            existingBuilding.setRentAreaEntities(newRentAreas);
            rentAreaService.addRentArea(existingBuilding); // Gọi hàm thêm mới
        }

        // Lưu thông tin tòa nhà đã cập nhật
        BuildingEntity updatedBuilding = buildingRepository.save(existingBuilding);

        // Ánh xạ lại sang DTO
        BuildingDTO updatedDTO = modelMapper.map(updatedBuilding, BuildingDTO.class);

        // ✅ Chuyển danh sách RentAreaEntity -> String để hiển thị đúng trong API
        updatedDTO.setRentArea(convertRentAreasToString(updatedBuilding.getRentAreaEntities()));

        return updatedDTO;
    }


    @Override
    public void deleteBuilding(Long id) {
        // ✅ Kiểm tra xem tòa nhà có tồn tại không
        BuildingEntity existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));

        // ✅ Xóa tất cả RentArea liên quan trước
        rentAreaRepository.deleteByBuilding(existingBuilding.getId());

        // ✅ Xóa tòa nhà sau khi đã xóa RentArea
        buildingRepository.delete(existingBuilding);
    }



}
