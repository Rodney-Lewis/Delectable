package com.delectable.ingredient;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import com.delectable.pantry.PantryItem;
import com.delectable.recipe.Recipe;
import com.delectable.unit.Unit;

@Entity
public class Ingredient implements Serializable {

    private static final long serialVersionUID = -5315453679206453478L;

    @Id
    @ManyToOne
    @MapsId("recipe_id")
    Recipe recipe;

    @Id
    @ManyToOne
    PantryItem pantry;

    String measurement;

    @ManyToOne
    Unit unit;

    public Ingredient(Recipe recipe, PantryItem pantry, String measurement, Unit unit) {
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

    public void setVolume(String measurement) {
        this.measurement = measurement;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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
