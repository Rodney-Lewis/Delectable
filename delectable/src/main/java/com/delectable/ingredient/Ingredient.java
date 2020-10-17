package com.delectable.ingredient;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.delectable.pantry.PantryItem;
import com.delectable.recipe.Recipe;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Ingredient implements Serializable {

    private static final long serialVersionUID = -5315453679206453478L;

    @Id
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    Recipe recipe;

    @Id
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    PantryItem pantry;

    String measurement;
    String unit;

    public Ingredient(Recipe recipe, PantryItem pantry, String measurement, String unit) {
        this.recipe = recipe;
        this.pantry = pantry;
        this.measurement = measurement;
        this.unit = unit;
    }

    public Ingredient() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public PantryItem getPantry() {
        return pantry;
    }

    public void setPantry(PantryItem pantry) {
        this.pantry = pantry;
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

    public enum Unit {
        TEASPOON("Teaspoon", "tsp"), TABLESPOON("Tablespoon", "tbsp"), CUP("Cup", "c"), PINT("Pint",
                "pt"), QUART("Quart", "qt"), GALLON("Gallon", "gal"), MILLILITER("Milliliter",
                        "ml"), LITER("Liter", "l"), FLUID_OUNCE("Fluid ounce", "fl oz"), OUNCE(
                                "Ounce", "oz"), POUND("Pound", "lb"), GRAMS("Gram", "g"), KILOGRAM(
                                        "Kilogram",
                                        "kg"), PACKET("Packet", "Packet"), TOTASTE("To taste",
                                                "To taste"), CAN("Can", "Can"), STICK("Stick",
                                                        "Stick"), BOX("Box", "Box"), PACKAGE(
                                                                "Package",
                                                                "Package"), JAR("Jar", "Jar"), DROP(
                                                                        "Drop",
                                                                        "Drop"), PINCH("Pinch",
                                                                                "Pinch"), DASH(
                                                                                        "Dash",
                                                                                        "Dash"), NONE(
                                                                                                "None",
                                                                                                "None");

        private final String name;
        private final String abbreviation;

        private Unit(String name, String abbreviation) {
            this.name = name;
            this.abbreviation = abbreviation;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static String[] namesToStringArray() {
            String[] stringArray = new String[values().length];
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i] = Unit.values()[i].name;
            }
            return stringArray;
        }

        public static String[] abbreviationsToStringArray() {
            String[] stringArray = new String[values().length];
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i] = Unit.values()[i].abbreviation;
            }
            return stringArray;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((measurement == null) ? 0 : measurement.hashCode());
        result = prime * result + ((pantry == null) ? 0 : pantry.hashCode());
        result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        if (measurement == null) {
            if (other.measurement != null)
                return false;
        } else if (!measurement.equals(other.measurement))
            return false;
        if (pantry == null) {
            if (other.pantry != null)
                return false;
        } else if (!pantry.equals(other.pantry))
            return false;
        if (recipe == null) {
            if (other.recipe != null)
                return false;
        } else if (!recipe.equals(other.recipe))
            return false;
        if (unit == null) {
            if (other.unit != null)
                return false;
        } else if (!unit.equals(other.unit))
            return false;
        return true;
    }

}
