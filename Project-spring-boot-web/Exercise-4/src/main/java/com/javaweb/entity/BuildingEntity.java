package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "building")
// Lưu trữ dữ liệu của tòa nhà, ánh xạ trực tiếp với bảng building trong database.
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
    private Long numberofbasement;

    @Column(name = "direction")
    private String direction;

    //update => sử dụng cascade  → Khi thêm, sửa, xóa BuildingEntity, Hibernate sẽ tự động thao tác với RentAreaEntity.
    //orphanRemoval = true → Nếu một RentAreaEntity bị loại khỏi danh sách rentAreaEntities, nó cũng bị xóa khỏi database.
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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
    private String servicefee;

    @Column(name = "electricityfee")
    private String electricityfee;

    @Column(name = "waterfee")
    private String waterfee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "brokeragefee")
    private String brokeragefee;

    @Column(name = "type")
    private String typeCode;

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


    public Long getNumberofbasement() {
        return numberofbasement;
    }

    public void setNumberofbasement(Long numberofbasement) {
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

    public String getServicefee() {
        return servicefee;
    }

    public void setServicefee(String servicefee) {
        this.servicefee = servicefee;
    }

    public String getElectricityfee() {
        return electricityfee;
    }

    public void setElectricityfee(String electricityfee) {
        this.electricityfee = electricityfee;
    }

    public String getWaterfee() {
        return waterfee;
    }

    public void setWaterfee(String waterfee) {
        this.waterfee = waterfee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getBrokeragefee() {
        return brokeragefee;
    }

    public void setBrokeragefee(String brokeragefee) {
        this.brokeragefee = brokeragefee;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}