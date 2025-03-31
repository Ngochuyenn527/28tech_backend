package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;


    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public BuildingDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Building ID must not be null!");
        }

        // Tìm tòa nhà theo ID, nếu không có thì ném ngoại lệ
        BuildingEntity entity = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tòa nhà với ID: " + id));

        // Chuyển đổi Entity -> DTO
        return buildingConverter.toBuildingDTO(entity);
    }


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
//


//    private List<String> toTypeCodeList(String typeCode) {
//        if (typeCode == null || typeCode.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return Arrays.asList(typeCode.split(","));
//    }

//    private String convertRentAreasToString(List<RentAreaEntity> rentAreas) {
//        return rentAreas.stream()
//                .map(ra -> String.valueOf(ra.getValue()))  // Lấy giá trị diện tích
//                .collect(Collectors.joining(","));
//    }

    @Override
    public BuildingDTO addBuilding(@Valid BuildingDTO buildingDTO) {

        // ✅ Chuyển từ DTO -> Entity
        BuildingEntity addbuildingEntity = buildingConverter.toBuildingEntity(buildingDTO);

        // ✅ Thêm mới rentArea vào database
        rentAreaRepository.saveAll(addbuildingEntity.getRentAreaEntities());

        // ✅ Lưu lại sau khi cập nhật danh sách RentArea
        buildingRepository.save(addbuildingEntity);

        return buildingDTO;
    }


    @Override
    public BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO) {
        // Kiểm tra tòa nhà có tồn tại không
        BuildingEntity existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));

        // Chuyển từ DTO -> Entity (giữ nguyên ID)
        BuildingEntity updatedBuildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        updatedBuildingEntity.setId(id);

        // Xóa RentArea cũ trước khi cập nhật mới
        rentAreaRepository.deleteByBuilding(id);

        // Thêm mới RentArea nếu có dữ liệu
        rentAreaRepository.saveAll(updatedBuildingEntity.getRentAreaEntities());


        // Lưu thông tin tòa nhà đã cập nhật
        buildingRepository.save(updatedBuildingEntity);

        return buildingDTO;
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
