package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long>{
    //xóa tất cả các RentAreaEntity liên quan đến một BuildingEntity cụ thể.
    //Spring Data JPA đã hỗ trợ xóa theo quan hệ thông qua phương thức deleteByBuilding(BuildingEntity buildingEntity).
    //không cần RentAreaRepositoryCustom hoặc RentAreaRepositoryImpl.

    void deleteByBuilding(BuildingEntity buildingEntity);
    // ✅ Thêm phương thức kiểm tra xem có diện tích thuê nào không
    boolean existsByBuilding(BuildingEntity building);
}
