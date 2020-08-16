package com.delectable.ingredient;

import com.delectable.ingredient.Ingredient.Unit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/ingredient")
@CrossOrigin
public class IngredientController {

    @GetMapping("/units")
    String[] getUnitsOfMeasurement() {
        return Unit.namesToStringArray();
    }

    @GetMapping("/units/abbreviations")
    String[] getUnitsOfMeasurementAbbreviations() {
        return Unit.abbreviationsToStringArray();
    }
}