package com.delectable.ingredient;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String measurement;
    String unit;

    public Ingredient() {
       super();
    }

    public Ingredient(String name, String measurement, String unit) {
        this.name = name;
        this.measurement = measurement;
        this.unit = unit;
    }

    public Ingredient(int id, String name, String measurement, String unit) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.unit = unit;
    }

    public enum MeasurementType {
        TEASPOONS("Teaspoon"),
        TABLESPOONS("Tablespoon"),
        CUPS("Cup"),
        FLUID_OUNCES("Fluid ounce"),
        PINTS("Pint"),
        LITERS("Liter"),
        QUARTS("Quart"),
        MILLILITERS("Milliliter"),
        OUNCES("Ounce"),
        POUNDS("Pound"),
        GRAMS("Gram"),
        KILOGRAMS("Kilogram"),
        PACKET("Packet"),
        TOTASTE("To taste");

        private final String name;
        private MeasurementType(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static String[] toStringArray() {
            String[] stringArray = new String[values().length];
            for(int i = 0; i < stringArray.length; i++) {
                stringArray[i] = MeasurementType.values()[i].name;
            }
            return stringArray;
        }
      }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
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