package com.delectable.recipe;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.delectable.ingredient.Ingredient;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String source;
	private int prepTimeHour;
	private int prepTimeMinute;
	private int prepTimeSecond;
	private int cookTimeHour;
	private int cookTimeMinute;
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