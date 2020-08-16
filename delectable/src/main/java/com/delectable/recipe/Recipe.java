package com.delectable.recipe;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.delectable.ingredient.Ingredient;
import com.delectable.pantry.PantryItem;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Name can not be null")
	private String name;

	@NotNull(message = "Source can not be null")
	private String source;

	private String description;

	@Min(value = 0, message = "Preparation time can not be negative.")
	private int prepTime;

	@Min(value = 0, message = "Cooking time can not be negative.")
	private int cookTime;

	@NotNull(message = "Ingredients can not be null")
	@OneToMany(mappedBy = "recipe")
	@Cascade(CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@NotNull(message = "Directions can not be null")
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Direction> directions = new ArrayList<Direction>();

	private String imageSource;
	private boolean deleted;

	public Recipe(@NotNull(message = "Name can not be null") String name,
			@NotNull(message = "Source can not be null") String source, String description,
			@Min(value = 0, message = "Preparation time can not be negative.") int prepTime,
			@Min(value = 0, message = "Cooking time can not be negative.") int cookTime,
			@NotNull(message = "Directions can not be null") List<Direction> directions,
			String imageSource) {
		this.name = name;
		this.source = source;
		this.description = description;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.directions = directions;
		this.imageSource = imageSource;
		this.deleted = false;
	}

	public Recipe() {
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public void addIngredientFromPantry(PantryItem pantryItem, String measurement, String unit) {
		Ingredient ingredient = new Ingredient(this, pantryItem, measurement, unit);
		ingredients.add(ingredient);
		pantryItem.getIngredients().add(ingredient);
	}

	public void removeIngredient(PantryItem pantryItem) {
		ingredients.removeIf(n -> pantryItem.equals(n.getPantry()) && this.equals((n.getRecipe())));
		pantryItem.getIngredients()
				.removeIf(n -> pantryItem.equals(n.getPantry()) && this.equals((n.getRecipe())));
	}

	public void setIngredientsFromPantryList(List<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredients) {
			addIngredientFromPantry(ingredient.getPantry(), ingredient.getMeasurement(),
					ingredient.getUnit());
		}
	}

}
