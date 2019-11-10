package com.delectable.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
public class Recipe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private int id;
	private String name;
	private String prepTime;
	private String cookTime;
	private String totalTime;
	private String source;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<RecipeStep> directions = new ArrayList<RecipeStep>();

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	@OneToMany(mappedBy = "recipe")
	List<Schedule> schedules = new ArrayList<Schedule>();

	public Recipe() {
		super();
	}

	public Recipe(String name, String prepTime, String cookTime, String source, List<RecipeStep> directions,
			List<Ingredient> ingredients) {
		this.name = name;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.source = source;
		setDirections(directions);
		setIngredients(ingredients);
		setTotalTime();
	}
	
	public Recipe(int id, String name, String prepTime, String cookTime, String source, List<RecipeStep> directions,
			List<Ingredient> ingredients) {
		this.id = id;
		this.name = name;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.source = source;
		this.directions = directions;
		this.ingredients = ingredients;
		setTotalTime();
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

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
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

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		for(Schedule schedule: schedules){
			addSchedule(schedule);
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

	public void addSchedule(Schedule schedule) {
		schedules.add(schedule);
		schedule.setRecipe(this);
	}

	public void removeSchedule(Schedule schedule) {
		schedules.remove(schedule);
		schedule.setRecipe(null);
	}

	public void setTotalTime() {
		if(!(cookTime == null || prepTime == null)) {
			totalTime = calculateTotalTime();
		}
	}

	public String getTotalTime() {
		return totalTime;
	}

	String calculateTotalTime() {
		
		String[] cookTimes = cookTime.split(":");
		String[] prepTimes = prepTime.split(":");

		long cookTimeSeconds = 0;
		long cookTimeMinutes = 0;
		long cookTimeHours = 0;
		long prepTimeSeconds = 0;
		long prepTimeMinutes = 0;
		long prepTimeHours = 0;
		long totalTimeSeconds = 0;
		long totalTimeMinutes = 0;
		long totalTimeHours = 0;

		switch(cookTimes.length) {
			case 1:
				cookTimeSeconds = TimeUnit.SECONDS.toSeconds(Long.parseLong(cookTimes[0]));
				cookTimeMinutes = 0;
				cookTimeHours = 0;
				break;
			case 2:
				cookTimeSeconds = TimeUnit.SECONDS.toSeconds(Long.parseLong(cookTimes[1]));
				cookTimeMinutes = TimeUnit.MINUTES.toSeconds(Long.parseLong(cookTimes[0]));
				cookTimeHours = 0;
				break;
			case 3:
				cookTimeSeconds = TimeUnit.SECONDS.toSeconds(Long.parseLong(cookTimes[2]));
				cookTimeMinutes = TimeUnit.MINUTES.toSeconds(Long.parseLong(cookTimes[1]));
				cookTimeHours = TimeUnit.HOURS.toSeconds(Long.parseLong(cookTimes[0]));;
				break;
		}
		
		switch(prepTimes.length) {
			case 1:
				prepTimeSeconds = TimeUnit.SECONDS.toSeconds(Long.parseLong(prepTimes[0]));
				prepTimeMinutes = 0;
				prepTimeHours = 0;
				break;
			case 2:
				prepTimeSeconds = TimeUnit.SECONDS.toSeconds(Long.parseLong(prepTimes[1]));
				prepTimeMinutes = TimeUnit.MINUTES.toSeconds(Long.parseLong(prepTimes[0]));
				prepTimeHours = 0;
				break;
			case 3:
				prepTimeSeconds = TimeUnit.SECONDS.toSeconds(Long.parseLong(prepTimes[2]));
				prepTimeMinutes = TimeUnit.MINUTES.toSeconds(Long.parseLong(prepTimes[1]));
				prepTimeHours = TimeUnit.HOURS.toSeconds(Long.parseLong(prepTimes[0]));
				break;
		}

		totalTimeSeconds = ((prepTimeSeconds + cookTimeSeconds) % 60);
		totalTimeMinutes = ((prepTimeMinutes + cookTimeMinutes) % 60) + ((prepTimeSeconds + cookTimeSeconds) / 60);
		totalTimeHours = ((prepTimeHours + cookTimeHours) % 60) + ((prepTimeHours + cookTimeHours) / 60);

		if(totalTimeHours != 0) {
			return(String.format("%1$d:%2$d:%3$d", totalTimeHours, totalTimeMinutes, totalTimeSeconds));
		} else if(totalTimeMinutes != 0) {
			return(String.format("%1$d:%2$d",totalTimeMinutes,totalTimeSeconds));
		} else {
			return (totalTime = String.format("%1$d", totalTimeSeconds));
		}
	}

	public Recipe getRecipe() {
	    return this;
	}
}