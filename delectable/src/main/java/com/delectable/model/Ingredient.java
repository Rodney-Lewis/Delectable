package com.delectable.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;


@Entity
public class Ingredient {
    @EmbeddedId
    IngredientKey id = new IngredientKey();

    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name="recipe_id")
    @JsonBackReference(value="rep")
    Recipe recipe = new Recipe();

    @ManyToOne
    @MapsId("pantryId")
    @JoinColumn(name="pantry_id")
    @JsonBackReference(value="pan")
    Pantry pantry = new Pantry();

    String quantity;
    String servingType;

    public Ingredient() {
       super();
    }

    public Ingredient(Pantry pantry, String quantity, String servingType) {
        this.id = new IngredientKey(recipe.getId(), pantry.getId());
        this.pantry = pantry;
        this.quantity = quantity;
        this.servingType = servingType;
    }
   
    public IngredientKey getId() {
        return id;
    }

    public void setId(IngredientKey id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
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