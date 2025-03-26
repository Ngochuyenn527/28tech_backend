package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest);

    public BuildingDTO addBuilding(BuildingDTO buildingDTO);

    public BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    public void deleteBuilding(Long id);

    public int countTotalItems(List<BuildingSearchResponse> list);

}
