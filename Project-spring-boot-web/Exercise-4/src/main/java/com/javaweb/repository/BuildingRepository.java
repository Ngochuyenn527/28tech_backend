package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    //Kế thừa JpaRepository<BuildingEntity, Long> để có sẵn các phương thức save, findById, delete,...
    //Không cần viết SQL thủ công vì Spring Data JPA hỗ trợ tự động.

}
