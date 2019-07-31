package com.delectable.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.delectable.controller.PantryController;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Recipe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private int id;
	private String name;
	private int prepTime;
	private int cookTime;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<RecipeStep> directions = new ArrayList<>();

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<Ingredient> ingredients = new ArrayList<>();
	private String source;

	public Recipe() {
		super();
	}

	public Recipe(String name, int prepTime, int cookTime, List<RecipeStep> directions,
			List<Ingredient> ingredients, String source) {
		this.name = name;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.directions = directions;
		this.ingredients = ingredients;
		this.source = source;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public List<RecipeStep> getDirections() {
		return directions;
	}

	public void setDirections(List<RecipeStep> directions) {
		for(RecipeStep step : directions) {
			addRecipeStep(step);
		}
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		for(Ingredient ingredient : ingredients) {
			addIngredient(ingredient);
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public void addRecipeStep(RecipeStep step) {
		directions.add(step);
		step.setRecipe(this);
	}

	public void removeRecipeStep(RecipeStep step) {
		directions.remove(step);
		step.setRecipe(null);
	}

	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
		ingredient.getPantry().getIngredients().add(ingredient);		
	}

	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
		ingredient.getPantry().getIngredients().remove(ingredient);
	}
}