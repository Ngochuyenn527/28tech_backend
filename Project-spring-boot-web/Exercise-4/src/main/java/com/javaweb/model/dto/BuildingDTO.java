package com.javaweb.model.dto;

import jakarta.validation.constraints.*;

import java.util.List;

//hứng data được điền trong các field của chức năng thêm, sửa tòa nhà
//dùng để giao tiếp với API
public class BuildingDTO extends AbstractDTO {

    @NotBlank(message = "Tên tòa nhà không được để trống")
    @Pattern(regexp = "^(?!\\d+$)[A-Za-zÀ-ỹ0-9 ]+$", message = "Tên tòa nhà phải chứa ít nhất một chữ cái")
    private String name;

    @NotBlank(message = "Cấu trúc tòa nhà không được để trống")
    @Pattern(regexp = "^(?!\\d+$)[A-Za-zÀ-ỹ0-9 ]+$", message = "Cấu trúc tòa nhà phải chứa ít nhất một chữ cái")
    private String structure;

    @NotBlank(message = "Quận không được để trống")
    private String district;

    @NotBlank(message = "Phường không được để trống")
    @Pattern(regexp = "^(?!\\d+$)[A-Za-zÀ-ỹ0-9 ]+$", message = "Phường phải chứa ít nhất một chữ cái")
    private String ward;

    @NotBlank(message = "Đường không được để trống")
    @Pattern(regexp = "^(?!\\d+$)[A-Za-zÀ-ỹ0-9 ]+$", message = "Đường phải chứa ít nhất một chữ cái")
    private String street;

    @NotNull(message = "Số tầng hầm không được để trống")
    @PositiveOrZero(message = "Số tầng hầm không được là số âm")
    private Long numberOfBasement;

    @NotBlank(message = "Hướng không được để trống")
    @Pattern(regexp = "^(?!\\d+$)[A-Za-zÀ-ỹ0-9 ]+$", message = "Hướng phải chứa ít nhất một chữ cái")
    private String direction;

    @NotBlank(message = "Diện tích thuê không được để trống")
    @Pattern(regexp = "^[0-9,]+$", message = "Diện tích thuê chỉ được chứa số và dấu phẩy")
    private String rentArea;

    @NotNull(message = "Giá thuê không được để trống")
    @Positive(message = "Giá thuê phải là số dương")
    private Long rentPrice;

    @NotBlank(message = "Phí dịch vụ không được để trống")
    @Positive(message = "Phí dịch vụ phải là số dương")
    private String serviceFee;

    @NotBlank(message = "Phí điện không được để trống")
    @Positive(message = "Phí điện phải là số dương")
    private String electricityFee;

    @NotBlank(message = "Phí nước không được để trống")
    @Positive(message = "Phí nước phải là số dương")
    private String waterFee;

    @NotBlank(message = "Tiền đặt cọc không được để trống")
    @Positive(message = "Tiền đặt cọc phải là số dương")
    private String deposit;

    @NotBlank(message = "Phí môi giới không được để trống")
    @Positive(message = "Phí môi giới phải là số dương")
    private String brokerageFee;

    private List<String> typeCode; // Danh sách loại tòa nhà có thể để trống

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

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(List<String> typeCode) {
        this.typeCode = typeCode;
    }
}