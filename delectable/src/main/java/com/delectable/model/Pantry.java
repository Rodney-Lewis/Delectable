package com.delectable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pantry_id")
    int id;
    String name;

    @OneToMany(mappedBy = "pantry")
    List<Ingredient> ingredients;

    public Pantry(String name) {
        this.name = name;
    }
}