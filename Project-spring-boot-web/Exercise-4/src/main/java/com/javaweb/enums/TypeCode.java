package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TypeCode {
    TANG_TRET("Tầng Trệt"),
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội Thất");

    private final String typeCodeName;

    TypeCode(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }

    public static Map<String, String> typeCodeMap() {
        Map<String, String> typeCodes = new TreeMap<>();
        for (TypeCode typeCode : TypeCode.values()) {
            typeCodes.put(typeCode.toString(), typeCode.typeCodeName);
        }
        return typeCodes;
    }
}
