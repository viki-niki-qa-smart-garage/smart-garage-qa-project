package com.api;

import java.util.Random;

public class Vehicles {

   private String brandName;
   private String modelName;
   private int year;
   private String engineType;
   private String vin;
   private String licensePlate;


    public Vehicles(String brandName, String modelName, int year, String engineType) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.year = year;
        this.engineType = engineType;
    }

    public Vehicles(String brandName, String modelName, int year, String engineType, String vin, String licensePlate) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.year = year;
        this.engineType = engineType;
        this.vin = vin;
        this.licensePlate = licensePlate;
    }
}
