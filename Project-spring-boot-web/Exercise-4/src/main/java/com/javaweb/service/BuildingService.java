package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface BuildingService {

    BuildingDTO findById(Long id);

    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO addBuilding(BuildingDTO buildingDTO);

    BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    void deleteBuilding(Long id);

    int countTotalItems(List<BuildingSearchResponse> list);

}
