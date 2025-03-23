package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "structure")
    private String structure;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "numberofbasement")
    private Integer numberofbasement;

    @Column(name = "direction")
    private String direction;

    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();

    public List<RentAreaEntity> getRentAreaEntities() {
        return rentAreaEntities;
    }

    public void setRentAreaEntities(List<RentAreaEntity> rentAreaEntities) {
        this.rentAreaEntities = rentAreaEntities;
    }

    @Column(name = "rentprice")
    private Long rentprice;

    @Column(name = "servicefee")
    private Long servicefee;

    @Column(name = "electricityfee")
    private Long electricityfee;

    @Column(name = "waterfee")
    private Long waterfee;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "brokeragefee")
    private Long brokeragefee;

    @Column(name = "type")
    private String typeCode;


//    // bổ sung thuộc tính từ bảng khác để DTO nhận được gtri lấy từ Entity
//    @Column(name = "managername")
//    private String managername;
//
//    @Column(name = "managerphonenumber")
//    private String managerphonenumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Integer getNumberofbasement() {
        return numberofbasement;
    }

    public void setNumberofbasement(Integer numberofbasement) {
        this.numberofbasement = numberofbasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getRentprice() {
        return rentprice;
    }

    public void setRentprice(Long rentprice) {
        this.rentprice = rentprice;
    }

    public Long getServicefee() {
        return servicefee;
    }

    public void setServicefee(Long servicefee) {
        this.servicefee = servicefee;
    }

    public Long getElectricityfee() {
        return electricityfee;
    }

    public void setElectricityfee(Long electricityfee) {
        this.electricityfee = electricityfee;
    }

    public Long getWaterfee() {
        return waterfee;
    }

    public void setWaterfee(Long waterfee) {
        this.waterfee = waterfee;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(Long brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}

