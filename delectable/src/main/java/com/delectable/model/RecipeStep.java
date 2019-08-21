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
    String instructions;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe = new Recipe();

    public RecipeStep() {
        super();
    }

    public RecipeStep(int step, String instructions, Recipe recipe) {
        this.step = step;
        this.instructions = instructions;
        this.recipe = recipe;
    }

    public RecipeStep(int id, int step, String instructions, Recipe recipe) {
        this.id = id;
        this.step = step;
        this.instructions = instructions;
        this.recipe = recipe;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getinstructions() {
        return instructions;
    }

    public void setinstructions(String instructions) {
        this.instructions = instructions;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}