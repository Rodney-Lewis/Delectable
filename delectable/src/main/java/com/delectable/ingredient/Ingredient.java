package com.delectable.ingredient;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    int id;

    @ManyToOne
    String pantry;
    String quantity;
    String servingType;

    public Ingredient() {
       super();
    }

    public Ingredient(String pantry, String quantity, String servingType) {
        this.pantry = pantry;
        this.quantity = quantity;
        this.servingType = servingType;
    }

    public Ingredient(int id, String pantry, String quantity, String servingType) {
        this.id = id;
        this.pantry = pantry;
        this.quantity = quantity;
        this.servingType = servingType;
    }

    public enum MeasurementType {
        TEASPOONS("Teaspoon(s)"),
        TABLESPOONS("Tablespoon(s)"),
        CUPS("Cup(s)"),
        FLUID_OUNCES("Fluid ounce(s)"),
        PINTS("Pint(s)"),
        LITERS("Liter(s)"),
        QUARTS("Quart(s)"),
        MILLILITERS("Milliliter(s)"),
        OUNCES("Ounce(s)"),
        POUNDS("Pound(s)"),
        GRAMS("Gram(s)"),
        KILOGRAMS("Kilogram(s)");

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

    public void setId(int id) {
        this.id = id;
    }

    public String getPantry() {
        return pantry;
    }

    public void setPantry(String pantry) {
        this.pantry = pantry;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }
}