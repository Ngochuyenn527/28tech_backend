package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building/")
@CrossOrigin
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public List<BuildingSearchResponse> getAllBuildings(@ModelAttribute BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> res = buildingService.searchBuildings(buildingSearchRequest);
        return res;
    }

    @PostMapping
    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        return ResponseEntity.ok(buildingService.addOrUpdateBuilding(buildingDTO));
    }


    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuildings(id);
    }
}
