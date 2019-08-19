package com.delectable.model;

import javax.persistence.Embeddable;
import javax.persistence.*;
import java.io.*;

@Embeddable
class IngredientKey implements Serializable {
 
    private static final long serialVersionUID = 1L;

    @Column(name = "recipe_id")
    int recipeId;
 
    @Column(name = "pantry_id")
    int pantryId;

    public IngredientKey() {
        super();
    }

    public IngredientKey(int recipeId, int pantryId) {
        this.recipeId = recipeId;
        this.pantryId = pantryId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getPantryId() {
        return pantryId;
    }

    public void setPantryId(int pantryId) {
        this.pantryId = pantryId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pantryId;
        result = prime * result + recipeId;
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
        IngredientKey other = (IngredientKey) obj;
        if (pantryId != other.pantryId)
            return false;
        if (recipeId != other.recipeId)
            return false;
        return true;
    }
    
}