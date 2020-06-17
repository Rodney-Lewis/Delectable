package com.delectable.ingredient;

import com.delectable.ingredient.Ingredient.MeasurementType;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/ingredient")
@CrossOrigin
public class IngredientController {

    @GetMapping("/measurements")
    String[] getMeasurements() {
        return MeasurementType.toStringArray();
    }
}