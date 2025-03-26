package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//    @PostMapping("/buildings")
//    //    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO) {
//    //        BuildingDTO newBuilding = buildingService.addBuilding(buildingDTO);
//    //        return ResponseEntity.status(HttpStatus.CREATED).body(newBuilding);
//    //    }

    @PostMapping
    public ResponseEntity<BuildingDTO> addBuilding(@RequestBody BuildingDTO buildingDTO) {
        BuildingDTO savedBuilding = buildingService.addBuilding(buildingDTO);
        return ResponseEntity.ok(savedBuilding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable Long id, @RequestBody BuildingDTO buildingDTO) {
        BuildingDTO updatedBuilding = buildingService.updateBuilding(id, buildingDTO);
        return ResponseEntity.ok(updatedBuilding);
    }




//    @PostMapping
//    public ResponseEntity<BuildingDTO> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
//        return ResponseEntity.ok(buildingService.addOrUpdateBuilding(buildingDTO));
//    }


    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable Long id) {
        //xuogn db de xoa theo id
        System.out.println("ok");
    }
}
