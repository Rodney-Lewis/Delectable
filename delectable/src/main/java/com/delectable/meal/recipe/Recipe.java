package com.delectable.meal.recipe;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.delectable.ingredient.Ingredient;
import com.delectable.meal.Direction;
import com.delectable.meal.Meal;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Recipe extends Meal {

	@NotNull(message = "Source cannot be null")
	private String source;

	@NotNull
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@NotNull
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Direction> directions = new ArrayList<Direction>();

	public Recipe() {
		super();
	}

	public Recipe(@NotNull(message = "Name cannot be null") String name,
			@Min(value = 0, message = "Preparation hours can not be negative.") int prepTimeHour,
			@Max(value = 59, message = "Preparation minutes can not be greater then 59.") @Min(value = 0, message = "Preparation minutes can not be negative") int prepTimeMinute,
			@Max(value = 59, message = "Preparation seconds can not be greater then 59.") @Min(value = 0, message = "Preparation seconds can not be negative") int prepTimeSecond,
			@Min(value = 0, message = "Cooking hours can not be negative.") int cookTimeHour,
			@Max(value = 59, message = "Cooking minutes can not be greater then 59.") @Min(value = 0, message = "Cooking minutes can not be negative") int cookTimeMinute,
			@Max(value = 59, message = "Cooking seconds can not be greater then 59.") @Min(value = 0, message = "Cooking seconds can not be negative") int cookTimeSecond,
			String imageSource, String description, @NotNull(message = "Source cannot be null") String source,
			@NotNull List<Ingredient> ingredients, @NotNull List<Direction> directions) {
		super(name, prepTimeHour, prepTimeMinute, prepTimeSecond, cookTimeHour, cookTimeMinute, cookTimeSecond, false,
				imageSource, description);
		this.source = source;
		this.ingredients = ingredients;
		this.directions = directions;
	}

	public Recipe(int id, @NotNull(message = "Name cannot be null") String name,
			@Min(value = 0, message = "Preparation hours can not be negative.") int prepTimeHour,
			@Max(value = 59, message = "Preparation minutes can not be greater then 59.") @Min(value = 0, message = "Preparation minutes can not be negative") int prepTimeMinute,
			@Max(value = 59, message = "Preparation seconds can not be greater then 59.") @Min(value = 0, message = "Preparation seconds can not be negative") int prepTimeSecond,
			@Min(value = 0, message = "Cooking hours can not be negative.") int cookTimeHour,
			@Max(value = 59, message = "Cooking minutes can not be greater then 59.") @Min(value = 0, message = "Cooking minutes can not be negative") int cookTimeMinute,
			@Max(value = 59, message = "Cooking seconds can not be greater then 59.") @Min(value = 0, message = "Cooking seconds can not be negative") int cookTimeSecond,
			String imageSource, String description, @NotNull(message = "Source cannot be null") String source,
			@NotNull List<Ingredient> ingredients, @NotNull List<Direction> directions) {
		super(id, name, prepTimeHour, prepTimeMinute, prepTimeSecond, cookTimeHour, cookTimeMinute, cookTimeSecond,
				false, imageSource, description);
		this.source = source;
		this.ingredients = ingredients;
		this.directions = directions;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}

	/*
	 * public void setIngredients(List<Ingredient> ingredients) { for (Ingredient
	 * ingredient : ingredients) { addIngredient(ingredient); } }
	 * 
	 * public void addIngredient(Ingredient ingredient) {
	 * ingredients.add(ingredient); }
	 * 
	 * public void removeIngredient(Ingredient ingredient) {
	 * ingredients.remove(ingredient); }
	 * 
	 * public void setDirections(List<Instruction> directions) { for (Instruction
	 * step : directions) { addRecipeStep(step); } }
	 * 
	 * public void addRecipeStep(Instruction step) { directions.add(step); }
	 * 
	 * public void removeRecipeStep(Instruction step) { directions.remove(step); }
	 */

}