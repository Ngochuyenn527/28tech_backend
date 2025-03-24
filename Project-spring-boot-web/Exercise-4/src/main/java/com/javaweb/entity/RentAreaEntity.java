package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {

    @Column(name = "value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public BuildingEntity getBuildingEntity() {
        return building;
    }

    public void setBuildingEntity(BuildingEntity buildingEntity) {
        this.building = buildingEntity;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }


}
