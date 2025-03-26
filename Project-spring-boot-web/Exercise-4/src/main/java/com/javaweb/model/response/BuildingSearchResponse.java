package com.javaweb.model.response;


import com.javaweb.model.dto.AbstractDTO;


//trả ra bảng dữ liệu tìm kiếm được (bảng 2)
public class BuildingSearchResponse extends AbstractDTO {
    private Long id;
    private String name;
    private String structure;
    private String address;
    private Long numberOfBasement;
    private String direction;
    //	private String managerName;
//	private String managerPhone;
//	private Long floorArea;
    private String rentArea;
    //    private String emptyArea;
    private Long rentPrice;
    private String serviceFee;
    private String brokerageFee;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}