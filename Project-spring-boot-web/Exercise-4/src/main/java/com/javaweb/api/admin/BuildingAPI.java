package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/building")
@Validated
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public List<BuildingSearchResponse> getAllBuildings(@ModelAttribute BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> res = buildingService.searchBuildings(buildingSearchRequest);
        return res;
    }


    @PostMapping
    public ResponseEntity<BuildingDTO> addBuilding(@Valid @RequestBody BuildingDTO buildingDTO) {
        BuildingDTO savedBuilding = buildingService.addBuilding(buildingDTO);
        return ResponseEntity.ok(savedBuilding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@Valid @PathVariable Long id, @RequestBody BuildingDTO buildingDTO) {
        BuildingDTO updatedBuilding = buildingService.updateBuilding(id, buildingDTO);
        return ResponseEntity.ok(updatedBuilding);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.ok("Deleted successfully!");
    }


}
