package com.javaweb.builder;

import java.util.List;

//chứa các field giống với BuildingSearchRequest
public class BuildingSearchBuilder {

    private String name;
    private String structure;
    private String district;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private List<String> typeCode;

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.structure = builder.structure;
        this.district = builder.district;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.direction = builder.direction;
        this.areaFrom = builder.areaFrom;
        this.areaTo = builder.areaTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.typeCode = builder.typeCode;
    }

    public String getName() {
        return name;
    }

    public String getStructure() {
        return structure;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public Long getAreaFrom() {
        return areaFrom;
    }

    public Long getAreaTo() {
        return areaTo;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public static class Builder {
        private String name;
        private String structure;
        private String district;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String direction;
        private Long areaFrom;
        private Long areaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private List<String> typeCode;


        public Builder setName(String name) {
            this.name = name;
            return this;

        }

        public Builder setStructure(String structure) {
            this.structure = structure;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }

        public Builder setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }

        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }

}

