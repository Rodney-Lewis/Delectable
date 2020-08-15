package com.delectable.pantry;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import com.delectable.ingredient.Ingredient;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PantryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name can not be null")
    private String name;

    private String brand;

    @NotNull
    @OneToMany(mappedBy = "pantry")
    private List<Ingredient> ingredients = new ArrayList<>();

    private String imageSource;
    private boolean deleted;
    private boolean schedulable;

    public PantryItem(@NotNull(message = "Name can not be null") String name, String brand) {
        this.name = name;
        this.brand = brand;
        this.deleted = false;
        this.schedulable = false;
    }

    public PantryItem(@NotNull(message = "Name can not be null") String name) {
        this.name = name;
        this.deleted = false;
        this.schedulable = false;
    }

    public PantryItem() {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public boolean isSchedulable() {
        return schedulable;
    }

    public void setSchedulable(boolean schedulable) {
        this.schedulable = schedulable;
    }
    
}
