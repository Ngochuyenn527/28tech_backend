package com.javaweb.repository;

import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {

    // Xóa tất cả RentArea theo Building
    @Modifying
    @Transactional
    @Query("DELETE FROM RentAreaEntity ra WHERE ra.building.id = :buildingId")
    void deleteByBuilding(@Param("buildingId") Long buildingId);
}
