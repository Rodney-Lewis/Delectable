package com.delectable.pantry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;


@Entity
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pantry_id")
    int id;

    String name;

    public Pantry() {
        super();
    }

    public Pantry(int id) {
        this.id = id;
    }

    public Pantry(String name) {
        this.name = name;
    }

    public Pantry(int id, String name) {
        this.id = id;
        this.name = name;
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
}