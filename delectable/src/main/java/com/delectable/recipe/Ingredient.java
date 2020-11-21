package com.delectable.recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ingredient  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String measurement;
    String unit;

    public Ingredient(String measurement, String unit) {
        this.measurement = measurement;
        this.unit = unit;
    }

    public Ingredient() {
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
