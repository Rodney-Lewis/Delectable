package com.delectable.meal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import com.delectable.meal.pantry.Pantry;
import com.delectable.meal.recipe.Recipe;
import com.delectable.meal.unit.Unit;

@Entity
public class RecipePantry {
    @EmbeddedId
    RecipePantryKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn("id")
    Recipe recipe;

    @ManyToOne
    @MapsId("id")
    @JoinColumn("id")
    Pantry pantry;

    String volume;
    Unit unit;
}