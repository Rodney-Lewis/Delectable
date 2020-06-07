package com.delectable.meal.preparedfood;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.delectable.meal.Instruction;
import com.delectable.meal.Meal;

@Entity
public class PreparedFood extends Meal {

    @NotNull(message = "Brand cannot be null")
    private String brand;

    @OneToMany
    private List<Instruction> directions = new ArrayList<Instruction>();

    public PreparedFood() {
        super();
    }

    public PreparedFood(@NotNull(message = "Name cannot be null") String name,
            @Min(value = 0, message = "Preparation hours can not be negative.") int prepTimeHour,
            @Max(value = 59, message = "Preparation minutes can not be greater then 59.") @Min(value = 0, message = "Preparation minutes can not be negative") int prepTimeMinute,
            @Max(value = 59, message = "Preparation seconds can not be greater then 59.") @Min(value = 0, message = "Preparation seconds can not be negative") int prepTimeSecond,
            @Min(value = 0, message = "Cooking hours can not be negative.") int cookTimeHour,
            @Max(value = 59, message = "Cooking minutes can not be greater then 59.") @Min(value = 0, message = "Cooking minutes can not be negative") int cookTimeMinute,
            @Max(value = 59, message = "Cooking seconds can not be greater then 59.") @Min(value = 0, message = "Cooking seconds can not be negative") int cookTimeSecond,
            String imageSource, String description, @NotNull(message = "Brand cannot be null") String brand,
            List<Instruction> directions) {
        super(name, prepTimeHour, prepTimeMinute, prepTimeSecond, cookTimeHour, cookTimeMinute, cookTimeSecond, false,
                imageSource, description);
        this.brand = brand;
        this.directions = directions;
    }

    public PreparedFood(int id, @NotNull(message = "Name cannot be null") String name,
            @Min(value = 0, message = "Preparation hours can not be negative.") int prepTimeHour,
            @Max(value = 59, message = "Preparation minutes can not be greater then 59.") @Min(value = 0, message = "Preparation minutes can not be negative") int prepTimeMinute,
            @Max(value = 59, message = "Preparation seconds can not be greater then 59.") @Min(value = 0, message = "Preparation seconds can not be negative") int prepTimeSecond,
            @Min(value = 0, message = "Cooking hours can not be negative.") int cookTimeHour,
            @Max(value = 59, message = "Cooking minutes can not be greater then 59.") @Min(value = 0, message = "Cooking minutes can not be negative") int cookTimeMinute,
            @Max(value = 59, message = "Cooking seconds can not be greater then 59.") @Min(value = 0, message = "Cooking seconds can not be negative") int cookTimeSecond,
            String imageSource, String description, @NotNull(message = "Brand cannot be null") String brand,
            List<Instruction> directions) {
        super(id, name, prepTimeHour, prepTimeMinute, prepTimeSecond, cookTimeHour, cookTimeMinute, cookTimeSecond,
                false, imageSource, description);
        this.brand = brand;
        this.directions = directions;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Instruction> getDirections() {
        return directions;
    }

    public void setDirections(List<Instruction> directions) {
        this.directions = directions;
    }

}