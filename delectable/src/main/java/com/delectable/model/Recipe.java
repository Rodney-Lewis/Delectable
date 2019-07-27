package com.delectable.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private int id;
	private String name;
	private int prepTime;
	private int cookTime;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeStep> directions;

	@OneToMany(mappedBy = "recipe")
    List<Ingredient> ingredients;
	private String source;

	public Recipe() {
		super();
	}

	public Recipe(int id, String name, int prepTime, int cookTime, List<RecipeStep> directions,
			List<Ingredient> ingredients, String source) {
		this.id = id;
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
		this.directions = directions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	
}