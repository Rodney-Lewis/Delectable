package com.delectable.meal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
    int step;
    String instruction;

    public Direction() {
        super();
    }

    public Direction(int step, String instruction) {
        this.step = step;
        this.instruction = instruction;
    }

    public Direction(int id, int step, String instruction) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}