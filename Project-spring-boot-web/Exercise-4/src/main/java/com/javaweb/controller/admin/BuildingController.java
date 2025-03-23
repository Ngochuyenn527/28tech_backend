package com.javaweb.controller.admin;

import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IUserService userService;

    //    ModelAndView sử dụng để Chuyển dữ liệu (Model) từ Controller sang View => Xác định View nào sẽ hiển thị dữ liệu
//    @GetMapping(value = "/admin/building-list")
//    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView("admin/building/list"); // Truyền tên View muốn hiển thị khi khởi tạo ModelAndView
//        mav.addObject("modelSearch", buildingSearchRequest); // => Được sd trong bảng tìm kiếm của building/list
//        //xuong db laays du lieu
//
//        List<BuildingSearchResponse> res = buildingService.searchBuildings(buildingSearchRequest);
//        BuildingSearchResponse buildingSearchResponseList = new BuildingSearchResponse();
//        buildingSearchResponseList.setListResult(res);
//
//        mav.addObject("districts", District.districtMap()); //Truyền danh sách quận đến View để hiển thị.
//        mav.addObject("typeCodes", TypeCode.typeCodeMap()); //Truyền danh sách loại hình tòa nhà (typeCodes).
//        mav.addObject("buildingListResponse", buildingSearchResponseList); //Truyền thông tin buildingDTO sang View để hiển thị dữ liệu tòa nhà cần chỉnh sửa.
//
//        return mav;
//    }

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("admin/building/list");

        // Truyền model tìm kiếm vào View
        mav.addObject("modelSearch", buildingSearchRequest);

        // Lấy dữ liệu từ DB
        List<BuildingSearchResponse> res = buildingService.searchBuildings(buildingSearchRequest);
        if (res == null) {
            res = new ArrayList<>(); // Tránh lỗi NullPointerException
        }

        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
        buildingSearchResponse.setListResult(res);
        buildingSearchResponse.setTotalItems(buildingService.countTotalItems(res));

        // Truyền danh sách vào View
        mav.addObject("buildingList", buildingSearchResponse);

        // Truyền danh sách quận và loại hình tòa nhà (tránh null)
        mav.addObject("districts", District.districtMap()); //Truyền danh sách quận đến View để hiển thị.
        mav.addObject("typeCodes", TypeCode.typeCodeMap());

        return mav;
    }

    //THEM
    @GetMapping(value = "/admin/building-edit") //gọi api
    public ModelAndView buildingEdit(@ModelAttribute("modelBuildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) { // => modelBuildingEdit Được sd trong bảng thêm và sửa của building/edit
        ModelAndView mav = new ModelAndView("admin/building/edit"); // ném ra 1 view trong package muốn gọi
        mav.addObject("districts", District.districtMap());
        mav.addObject("typeCodes", TypeCode.typeCodeMap());
        return mav;
    }

    //SUA
    @GetMapping(value = "/admin/building-edit-{id}") //gọi api
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit"); // Tạo một ModelAndView trỏ đến file giao diện (admin/building/edit.jsp
        //xuong db timf building theo id
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        buildingDTO.setName("H Building");

        mav.addObject("districts", District.districtMap());
        mav.addObject("typeCodes", TypeCode.typeCodeMap());
        mav.addObject("modelBuildingEdit", buildingDTO);
        return mav;
    }


}
