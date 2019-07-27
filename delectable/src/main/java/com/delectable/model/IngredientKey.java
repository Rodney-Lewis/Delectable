package com.delectable.model;

import javax.persistence.Embeddable;
import javax.persistence.*;
import java.io.*;

@Embeddable
class IngredientKey implements Serializable {
 
    static final long serialVersionUID = 0;

    @Column(name = "recipe_id")
    Long studentId;
 
    @Column(name = "pantry_id")
    Long courseId;
}