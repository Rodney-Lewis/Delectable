package com.delectable.recipe;

import javax.validation.constraints.Max;
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

	private String source;

	@Column(columnDefinition = "text")
	private String description;

	@Min(value = 0, message = "Can not be negative.")
	private int prepTimeHour;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private int prepTimeMinute;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private int prepTimeSecond;

	@Min(value = 0, message = "Can not be negative.")
	private int cookTimeHour;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private int cookTimeMinute;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private int cookTimeSecond;

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

	public Recipe() {
	}

	public Recipe(@NotNull(message = "Name can not be null") String name, String source,
			String description, @Min(value = 0, message = "Can not be negative.") int prepTimeHour,
			@Max(value = 59, message = "Can not expceed 59") @Min(value = 0,
					message = "Can not be negative.") int prepTimeMinute,
			@Max(value = 59, message = "Can not expceed 59") @Min(value = 0,
					message = "Can not be negative.") int prepTimeSecond,
			@Min(value = 0, message = "Can not be negative.") int cookTimeHour,
			@Max(value = 59, message = "Can not expceed 59") @Min(value = 0,
					message = "Can not be negative.") int cookTimeMinute,
			@Max(value = 59, message = "Can not expceed 59") @Min(value = 0,
					message = "Can not be negative.") int cookTimeSecond,
			@NotNull(message = "Directions can not be null") List<Direction> directions,
			String imageSource, boolean deleted) {
		this.name = name;
		this.source = source;
		this.description = description;
		this.prepTimeHour = prepTimeHour;
		this.prepTimeMinute = prepTimeMinute;
		this.prepTimeSecond = prepTimeSecond;
		this.cookTimeHour = cookTimeHour;
		this.cookTimeMinute = cookTimeMinute;
		this.cookTimeSecond = cookTimeSecond;
		this.directions = directions;
		this.imageSource = imageSource;
		this.deleted = deleted;
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

	public void addIngredientFromPantry(PantryItem pantryItem, String measurement, String unit) {
		Ingredient ingredient = new Ingredient(this, pantryItem, measurement, unit);
		ingredients.add(ingredient);
		pantryItem.getIngredients().add(ingredient);
	}

	public void removeIngredient(PantryItem pantryItem) {
		ingredients.removeIf(n -> pantryItem.equals(n.getPantry()) && this.equals((n.getRecipe())));
		pantryItem.getIngredients().removeIf(n -> pantryItem.equals(n.getPantry()) && this.equals((n.getRecipe())));
	}

	public void setIngredientsFromPantryList(List<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredients) {
			addIngredientFromPantry(ingredient.getPantry(), ingredient.getMeasurement(),
					ingredient.getUnit());
		}
	}

}
