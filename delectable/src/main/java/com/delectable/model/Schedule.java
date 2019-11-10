package com.delectable.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
    int id; 
   
    @ManyToOne
    @JoinColumn(name="recipe_id")
    Recipe recipe = new Recipe();
     
    long epoch;
    String mealType;

    public enum MealTypes {
        DINNER("Dinner"),
        LUNCH("Lunch"),
        BREAKFAST("Breakfast");

        private final String name;
        private MealTypes(String name){
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
      }

    public Schedule(){}

    public Schedule(long epoch, String mealType, Recipe recipe) {
        this.epoch = epoch;
        this.recipe = recipe;
        setMealType(mealType);
    }

    public Schedule(int id, long epoch, String mealType, Recipe recipe) {
        this.id = id;
        this.epoch = epoch;
        this.recipe = recipe;
        setMealType(mealType);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        for(MealTypes type : Schedule.MealTypes.values()) {
            if(type.toString() == mealType) {
                this.mealType = mealType;
            }
        }
    }

}