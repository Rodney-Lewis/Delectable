package com.delectable.recipe;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.delectable.schedule.Schedule;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Name can not be null")
	private String name;

	private String source;

	@Column(columnDefinition = "text")
	private String description;

	@Min(value = 0, message = "Can not be negative.")
	private short prepTimeHour;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private byte prepTimeMinute;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private byte prepTimeSecond;

	@Min(value = 0, message = "Can not be negative.")
	private short cookTimeHour;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private byte cookTimeMinute;

	@Max(value = 59, message = "Can not expceed 59")
	@Min(value = 0, message = "Can not be negative.")
	private byte cookTimeSecond;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Direction> directions = new ArrayList<Direction>();

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Schedule> schedule = new ArrayList<Schedule>();

	@ElementCollection
	private List<String> tags = new ArrayList<String>();

	private String imageSource;
	private boolean deleted;

	public Recipe() {
		this.deleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public short getPrepTimeHour() {
		return prepTimeHour;
	}

	public void setPrepTimeHour(short prepTimeHour) {
		this.prepTimeHour = prepTimeHour;
	}

	public byte getPrepTimeMinute() {
		return prepTimeMinute;
	}

	public void setPrepTimeMinute(byte prepTimeMinute) {
		this.prepTimeMinute = prepTimeMinute;
	}

	public byte getPrepTimeSecond() {
		return prepTimeSecond;
	}

	public void setPrepTimeSecond(byte prepTimeSecond) {
		this.prepTimeSecond = prepTimeSecond;
	}

	public short getCookTimeHour() {
		return cookTimeHour;
	}

	public void setCookTimeHour(short cookTimeHour) {
		this.cookTimeHour = cookTimeHour;
	}

	public byte getCookTimeMinute() {
		return cookTimeMinute;
	}

	public void setCookTimeMinute(byte cookTimeMinute) {
		this.cookTimeMinute = cookTimeMinute;
	}

	public byte getCookTimeSecond() {
		return cookTimeSecond;
	}

	public void setCookTimeSecond(byte cookTimeSecond) {
		this.cookTimeSecond = cookTimeSecond;
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

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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

}
