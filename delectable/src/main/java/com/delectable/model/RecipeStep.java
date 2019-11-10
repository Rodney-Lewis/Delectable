package com.delectable.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
	int id;
    int step;
    String instructions;

    public RecipeStep() {
        super();
    }

    public RecipeStep(int step, String instructions) {
        this.step = step;
        this.instructions = instructions;
    }

    public RecipeStep(int id, int step, String instructions) {
        this.id = id;
        this.step = step;
        this.instructions = instructions;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}