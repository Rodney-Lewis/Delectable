package com.delectable.recipe;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int step;

    @Column(columnDefinition = "text")
    String instruction;

    public Direction() {
    }

    public Direction(int step, String instruction) {
        this.step = step;
        this.instruction = instruction;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getinstruction() {
        return instruction;
    }

    public void setinstruction(String instruction) {
        this.instruction = instruction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
