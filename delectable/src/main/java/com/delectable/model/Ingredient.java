package com.delectable.model;

import javax.persistence.*;

@Entity
class Ingredient {
    @EmbeddedId
    IngredientKey id;

    @ManyToOne
    @MapsId("recipe_id")
    @JoinColumn(name="recipe_id")
    Recipe recipe;

    @ManyToOne
    @MapsId("pantry_id")
    @JoinColumn(name="pantry_id")
    Pantry pantry;

    String quantity;
    String servingType;
}