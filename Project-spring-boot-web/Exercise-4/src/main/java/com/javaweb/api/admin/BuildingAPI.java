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

//    @PostMapping
//    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
//        return ResponseEntity.ok(buildingService.addOrUpdateBuilding(buildingDTO));
//    }

    //POST (tạo mới) → Bắt buộc id phải null.
    @PostMapping
    public ResponseEntity<?> createBuilding(@RequestBody BuildingDTO buildingDTO) {
        // ✅ Khi tạo mới, không cần ID
        buildingDTO.setId(null);

        BuildingDTO savedBuilding = buildingService.addOrUpdateBuilding(buildingDTO);
        return ResponseEntity.ok(savedBuilding);
    }

    //PUT (cập nhật) → Bắt buộc id phải có (@PathVariable Long id).
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBuilding(@PathVariable Long id, @RequestBody BuildingDTO buildingDTO) {
        // ✅ Khi cập nhật, ID phải tồn tại
        buildingDTO.setId(id);

        BuildingDTO updatedBuilding = buildingService.addOrUpdateBuilding(buildingDTO);
        return ResponseEntity.ok(updatedBuilding);
    }


    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
    }
}
