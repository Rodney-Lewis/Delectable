package com.delectable.meal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Name cannot be null")
	private String name;

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

	@NotNull
	private boolean deleted;

	// Optional fields
	private String imageSource;
	private String description;

	public Meal() {
		super();
	}

	public Meal(@NotNull(message = "Name cannot be null") String name,
			@Min(value = 0, message = "Preparation hours can not be negative.") int prepTimeHour,
			@Max(value = 59, message = "Preparation minutes can not be greater then 59.") @Min(value = 0, message = "Preparation minutes can not be negative") int prepTimeMinute,
			@Max(value = 59, message = "Preparation seconds can not be greater then 59.") @Min(value = 0, message = "Preparation seconds can not be negative") int prepTimeSecond,
			@Min(value = 0, message = "Cooking hours can not be negative.") int cookTimeHour,
			@Max(value = 59, message = "Cooking minutes can not be greater then 59.") @Min(value = 0, message = "Cooking minutes can not be negative") int cookTimeMinute,
			@Max(value = 59, message = "Cooking seconds can not be greater then 59.") @Min(value = 0, message = "Cooking seconds can not be negative") int cookTimeSecond,
			@NotNull boolean deleted, String imageSource, String description) {
		this.name = name;
		this.prepTimeHour = prepTimeHour;
		this.prepTimeMinute = prepTimeMinute;
		this.prepTimeSecond = prepTimeSecond;
		this.cookTimeHour = cookTimeHour;
		this.cookTimeMinute = cookTimeMinute;
		this.cookTimeSecond = cookTimeSecond;
		this.deleted = deleted;
		this.imageSource = imageSource;
		this.description = description;
	}

	public Meal(int id, @NotNull(message = "Name cannot be null") String name,
			@Min(value = 0, message = "Preparation hours can not be negative.") int prepTimeHour,
			@Max(value = 59, message = "Preparation minutes can not be greater then 59.") @Min(value = 0, message = "Preparation minutes can not be negative") int prepTimeMinute,
			@Max(value = 59, message = "Preparation seconds can not be greater then 59.") @Min(value = 0, message = "Preparation seconds can not be negative") int prepTimeSecond,
			@Min(value = 0, message = "Cooking hours can not be negative.") int cookTimeHour,
			@Max(value = 59, message = "Cooking minutes can not be greater then 59.") @Min(value = 0, message = "Cooking minutes can not be negative") int cookTimeMinute,
			@Max(value = 59, message = "Cooking seconds can not be greater then 59.") @Min(value = 0, message = "Cooking seconds can not be negative") int cookTimeSecond,
			@NotNull boolean deleted, String imageSource, String description) {
		this.id = id;
		this.name = name;
		this.prepTimeHour = prepTimeHour;
		this.prepTimeMinute = prepTimeMinute;
		this.prepTimeSecond = prepTimeSecond;
		this.cookTimeHour = cookTimeHour;
		this.cookTimeMinute = cookTimeMinute;
		this.cookTimeSecond = cookTimeSecond;
		this.deleted = deleted;
		this.imageSource = imageSource;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}