package com.delectable.controller;

import com.delectable.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.delectable.model.Recipe;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/recipe/add")
    void addRecipe(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
    }

    @GetMapping("/recipe/get")
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeService.findAll();
    }

    @GetMapping("/recipe/get/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        if(!recipe.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            return recipe.get();
        }
    }

    @PutMapping("recipe/update")
    void updateRecipe(@RequestBody Recipe recipe) {
        Optional<Recipe> newRecipe = recipeService.findById(recipe.getId());
        if(!newRecipe.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            recipeService.save(recipe);
        }
    }

    @DeleteMapping("/recipe/delete/{id}")
    void deleteRecipe(@PathVariable int id) {
        recipeService.deleteById(id);
    }
}