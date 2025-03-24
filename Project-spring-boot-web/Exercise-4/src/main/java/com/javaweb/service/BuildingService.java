package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest);

    public void deleteBuilding(Long buildingId);

    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO);

    public BuildingDTO findById(Long id);

    public int countTotalItems(List<BuildingSearchResponse> list);

}
