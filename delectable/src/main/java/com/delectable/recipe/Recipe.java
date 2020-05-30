package com.delectable.recipe;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.delectable.ingredient.Ingredient;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Name cannot be null")
	private String name;

	@NotNull(message = "Source cannot be null")
	private String source;

	@Min(value = 0, message = "Preparation hours can not be negative.")
	private int prepTimeHour;

	@Max(value = 59, message = "Preparation minutes can not be greater then 59.")
	@Min(value = 0, message = "Preparation minutes can not be negative")
	private int prepTimeMinute;

	@Max(value = 59, message = "Preparation seconds can not be greater then 59.")
	@Min(value = 0, message = "Preparation seconds can not be negative")
	private int prepTimeSecond;

	@Min(value = 0, message = "Cooking hours can not be negative.")
	private int cookTimeHour;

	@Max(value = 59, message = "Cooking minutes can not be greater then 59.")
	@Min(value = 0, message = "Cooking minutes can not be negative")
	private int cookTimeMinute; 
	
	@Max(value = 59, message = "Cooking seconds can not be greater then 59.")
	@Min(value = 0, message = "Cooking seconds can not be negative")
	private int cookTimeSecond;
	
	private String imageSource;
	private boolean deleted;

	@OneToMany(cascade = CascadeType.ALL)
	List<RecipeStep> directions = new ArrayList<RecipeStep>();

	@OneToMany(cascade = CascadeType.ALL)
	List<Ingredient> ingredients = new ArrayList<Ingredient>();

	public Recipe() {
		super();
	}

	public Recipe(int id, String name, String source, int prepTimeHour, int prepTimeMinute, int prepTimeSecond,
			int cookTimeHour, int cookTimeMinute, int cookTimeSecond, String imageSource) {
		this.id = id;
		this.name = name;
		this.source = source;
		this.prepTimeHour = prepTimeHour;
		this.prepTimeMinute = prepTimeMinute;
		this.prepTimeSecond = prepTimeSecond;
		this.cookTimeHour = cookTimeHour;
		this.cookTimeMinute = cookTimeMinute;
		this.cookTimeSecond = cookTimeSecond;
		this.imageSource = imageSource;
		this.deleted = false;
	}

	public Recipe(int id, String name, String source, int prepTimeHour, int prepTimeMinute, int prepTimeSecond,
			int cookTimeHour, int cookTimeMinute, int cookTimeSecond, String imageSource, List<RecipeStep> directions,
			List<Ingredient> ingredients) {
		this.id = id;
		this.name = name;
		this.source = source;
		this.prepTimeHour = prepTimeHour;
		this.prepTimeMinute = prepTimeMinute;
		this.prepTimeSecond = prepTimeSecond;
		this.cookTimeHour = cookTimeHour;
		this.cookTimeMinute = cookTimeMinute;
		this.cookTimeSecond = cookTimeSecond;
		this.imageSource = imageSource;
		this.deleted = false;
		setDirections(directions);
		setIngredients(ingredients);
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

	public List<RecipeStep> getDirections() {
		return directions;
	}

	public void setDirections(List<RecipeStep> directions) {
		for (RecipeStep step : directions) {
			addRecipeStep(step);
		}
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredients) {
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
	}

	public void removeRecipeStep(RecipeStep step) {
		directions.remove(step);
	}

	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public int getPrepTimeHour() {
		return prepTimeHour;
	}

	public void setPrepTimeHour(int prepTimeHour) {
		this.prepTimeHour = prepTimeHour;
	}

	public int getPrepTimeMinute() {
		return prepTimeMinute;
	}

	public void setPrepTimeMinute(int prepTimeMinute) {
		this.prepTimeMinute = prepTimeMinute;
	}

	public int getPrepTimeSecond() {
		return prepTimeSecond;
	}

	public void setPrepTimeSecond(int prepTimeSecond) {
		this.prepTimeSecond = prepTimeSecond;
	}

	public int getCookTimeHour() {
		return cookTimeHour;
	}

	public void setCookTimeHour(int cookTimeHour) {
		this.cookTimeHour = cookTimeHour;
	}

	public int getCookTimeMinute() {
		return cookTimeMinute;
	}

	public void setCookTimeMinute(int cookTimeMinute) {
		this.cookTimeMinute = cookTimeMinute;
	}

	public int getCookTimeSecond() {
		return cookTimeSecond;
	}

	public void setCookTimeSecond(int cookTimeSecond) {
		this.cookTimeSecond = cookTimeSecond;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}