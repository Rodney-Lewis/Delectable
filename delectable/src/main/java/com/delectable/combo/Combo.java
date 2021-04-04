package com.delectable.combo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.delectable.recipe.*;
import com.delectable.restaurant.*;
import com.delectable.shared.crud.CRUSoftDeleteEntity;
import io.micrometer.core.lang.NonNull;

@Entity
public class Combo implements CRUSoftDeleteEntity<Combo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String name;

    @OneToMany
    private List<Recipe> recipe = new ArrayList<Recipe>();

    @OneToMany
    private List<Restaurant> restaurant = new ArrayList<Restaurant>();

    boolean deleted;

    public Combo() {
        this.deleted = false;
    }

    public Combo(String name, List<Recipe> recipe, List<Restaurant> restaurant) {
        this.name = name;
        this.recipe = recipe;
        this.restaurant = restaurant;
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

    public List<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<Recipe> recipe) {
        this.recipe = recipe;
    }

    public List<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
