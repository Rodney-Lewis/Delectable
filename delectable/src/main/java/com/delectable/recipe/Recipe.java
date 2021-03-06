package com.delectable.recipe;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.delectable.shared.crud.CRUSoftDeleteEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Recipe implements CRUSoftDeleteEntity<Recipe> {

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

	@Min(value = 0, message = "Can not be negative.")
	private short servings;

	@ManyToMany
	@Cascade(CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();

	@ManyToMany
	@Cascade(CascadeType.ALL)
	private List<Direction> directions = new ArrayList<Direction>();

	@ElementCollection
	private List<String> tags = new ArrayList<String>();

	private String imageSource;
	private boolean deleted;

	public Recipe() {
		this.deleted = false;
	}

	public Recipe(@NotNull(message = "Name can not be null") String name, String source,
			String description,
			@Min(value = 0, message = "Can not be negative.") short prepTimeHour,
			@Max(value = 59, message = "Can not expceed 59")
			@Min(value = 0, message = "Can not be negative.") byte prepTimeMinute,
			@Max(value = 59, message = "Can not expceed 59")
			@Min(value = 0, message = "Can not be negative.") byte prepTimeSecond,
			@Min(value = 0, message = "Can not be negative.") short cookTimeHour,
			@Max(value = 59, message = "Can not expceed 59")
			@Min(value = 0, message = "Can not be negative.") byte cookTimeMinute,
			@Max(value = 59, message = "Can not expceed 59")
			@Min(value = 0, message = "Can not be negative.") byte cookTimeSecond,
			@Min(value = 0, message = "Can not be negative.") short servings,
			List<Ingredient> ingredients, List<Direction> directions, List<String> tags,
			String imageSource) {
		this.name = name;
		this.source = source;
		this.description = description;
		this.prepTimeHour = prepTimeHour;
		this.prepTimeMinute = prepTimeMinute;
		this.prepTimeSecond = prepTimeSecond;
		this.cookTimeHour = cookTimeHour;
		this.cookTimeMinute = cookTimeMinute;
		this.cookTimeSecond = cookTimeSecond;
		this.servings = servings;
		this.ingredients = ingredients;
		this.directions = directions;
		this.tags = tags;
		this.imageSource = imageSource;
		this.deleted = false;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
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

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public short getServings() {
		return servings;
	}

	public void setServings(short servings) {
		this.servings = servings;
	}

}
