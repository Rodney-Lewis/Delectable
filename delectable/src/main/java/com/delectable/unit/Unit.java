package com.delectable.unit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    String name;

    public Unit() {
        super();
    }

    public Unit (String name) {
        this.name = name;
    }

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * TEASPOONS("Teaspoon"), TABLESPOONS("Tablespoon"), CUPS("Cup"), FLUID_OUNCES("Fluid ounce"),
     * PINTS("Pint"), LITERS("Liter"), QUARTS("Quart"), MILLILITERS("Milliliter"), OUNCES("Ounce"),
     * POUNDS("Pound"), GRAMS("Gram"), KILOGRAMS("Kilogram"), PACKET("Packet"), TOTASTE("To taste");
     */

}