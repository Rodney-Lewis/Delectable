package com.delectable.model;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    int id;

    @ManyToOne
    Pantry pantry = new Pantry();

    String quantity;
    String servingType;

    public Ingredient() {
       super();
    }

    public Ingredient(Pantry pantry, String quantity, String servingType) {
        this.pantry = pantry;
        this.quantity = quantity;
        this.servingType = servingType;
    }

    public Ingredient(int id, Pantry pantry, String quantity, String servingType) {
        this.id = id;
        this.pantry = pantry;
        this.quantity = quantity;
        this.servingType = servingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }
}