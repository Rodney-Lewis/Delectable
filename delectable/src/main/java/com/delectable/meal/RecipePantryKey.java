package com.delectable.meal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipePantryKey implements Serializable {

    private static final long serialVersionUID = 7294615673804923458L;

    @Column(name = "recipe_id")
    int recipeId;

    @Column(name = "pantry_id")
    int pantryId;

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

}
