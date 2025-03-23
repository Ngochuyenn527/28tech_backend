package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

//Enum giúp định nghĩa danh sách quận mà ít bị thay đổi trong CSDL.
//Giảm thiểu số lần truy vấn SQL, giúp tăng hiệu suất hệ thống.
//Dễ dàng quản lý & sử dụng trong code hơn so với lưu trực tiếp trong DB.
public enum District {

    //QUAN_1 là hằng số đại diện cho "Quận 1".
//    ... ✔ Dữ liệu lưu dưới dạng key-value:
//    Key: QUAN_1, QUAN_2, ...
//    Value: "Quận 1", "Quận 2", ...
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5");

    private final String districtName;

    District(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> districtMap() {
        Map<String, String> districts = new TreeMap<>(); //✔ Tạo một Map<String, String> chứa danh sách quận:
        for (District district : District.values()) { //✔ Duyệt qua tất cả giá trị enum bằng District.values()
            districts.put(district.toString(), district.districtName);   //<key,value> => <"QUAN_1", "Quận 1">
        }
        return districts;
    }
}
