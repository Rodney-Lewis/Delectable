package com.delectable.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
	private int id;
    int step;
    String intrustructions;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    public RecipeStep() {
        super();
    }

    public RecipeStep(int step, String intrustructions, Recipe recipe) {
        this.step = step;
        this.intrustructions = intrustructions;
        this.recipe = recipe;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getIntrustructions() {
        return intrustructions;
    }

    public void setIntrustructions(String intrustructions) {
        this.intrustructions = intrustructions;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}