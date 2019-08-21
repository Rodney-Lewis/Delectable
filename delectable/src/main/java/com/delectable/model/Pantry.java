package com.delectable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pantry_id")
    int id;

    String name;
    
    @OneToMany(mappedBy = "pantry")
    List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Pantry() {
        super();
    }

    public Pantry(int id) {
        this.id = id;
    }

    public Pantry(String name) {
        this.name = name;
    }

    public Pantry(int id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
 
}