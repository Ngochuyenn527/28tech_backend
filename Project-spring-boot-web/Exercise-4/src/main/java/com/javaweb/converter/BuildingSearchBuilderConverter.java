package com.javaweb.converter;


import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;


//@Component
@Component
// chuyển những giá trị của BuildingSearchRequest cần tìm kiếm thành BuildingSearchBuilder để giải quyết vấn đề Constructor có quá nhiều tham số
// (có thể tham khảo builder pattern để hiểu rõ hơn)
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObjects(buildingSearchRequest.getName(), String.class))
                .setStructure(MapUtils.getObjects(buildingSearchRequest.getStructure(), String.class))
                .setWard(MapUtils.getObjects(buildingSearchRequest.getWard(), String.class))
                .setStreet(MapUtils.getObjects(buildingSearchRequest.getStreet(), String.class))
                .setDistrict(MapUtils.getObjects(buildingSearchRequest.getDistrict(), String.class))
                .setNumberOfBasement(MapUtils.getObjects(buildingSearchRequest.getNumberOfBasement(), Long.class))
                .setDirection(MapUtils.getObjects(buildingSearchRequest.getDirection(), String.class))
                .setRentPriceTo(MapUtils.getObjects(buildingSearchRequest.getRentPriceTo(), Long.class))
                .setRentPriceFrom(MapUtils.getObjects(buildingSearchRequest.getRentPriceFrom(), Long.class))
                .setAreaFrom(MapUtils.getObjects(buildingSearchRequest.getAreaFrom(), Long.class))
                .setAreaTo(MapUtils.getObjects(buildingSearchRequest.getAreaTo(), Long.class))
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .build();

        return buildingSearchBuilder;
    }
}
