package com.api;

public class Vehicles {
   private String brandName;
   private String modelName;
   private int year;
   private String engineType;


    public Vehicles(String brandName, String modelName, int year, String engineType) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.year = year;
        this.engineType = engineType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}
